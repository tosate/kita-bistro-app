package de.kitaggmbhtrier.bistro.portal.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import de.kitaggmbhtrier.bistro.data.KindergartenChild;
import de.kitaggmbhtrier.bistro.data.KindergartenChildComparator;
import de.kitaggmbhtrier.bistro.data.KindergartenGroup;
import de.kitaggmbhtrier.bistro.data.Meal;
import de.kitaggmbhtrier.bistro.data.MealType;
import de.kitaggmbhtrier.bistro.portal.util.PortalUtil;
import de.kitaggmbhtrier.bistro.repository.KindergartenChildRepository;
import de.kitaggmbhtrier.bistro.repository.KindergartenGroupRepository;
import de.kitaggmbhtrier.bistro.repository.MealRepository;

@Controller
public class AdminChildrenController {

	@Autowired
	private KindergartenChildRepository kindergartenChildRepository;
	@Autowired
	private KindergartenGroupRepository kindergartenGroupRepository;
	@Autowired
	private MealRepository mealRepository;

	public static final String URL_FETCH_CHILDREN_JSON = AdminController.URL_ADMIN + "/fetch/children";
	public static final String URL_CREATE_CHILD_JSON = AdminController.URL_ADMIN + "/create/child";

	private static final String DATE_PATTERN = "yyyy-MM-dd";

	@RequestMapping(value = URL_FETCH_CHILDREN_JSON, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<KindergartenChild> fetchChildren() {
		List<KindergartenChild> result = new ArrayList<>();
		Iterator<KindergartenChild> childrenIterator = kindergartenChildRepository.findAll().iterator();
		while (childrenIterator.hasNext()) {
			result.add(childrenIterator.next());
		}
		Collections.sort(result, new KindergartenChildComparator());

		return result;
	}

	@RequestMapping(value = URL_CREATE_CHILD_JSON, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ControllerResponse createChild(@RequestParam String firstName, @RequestParam String lastName,
			@RequestParam String groupIdValue, @RequestParam String kitaStartDate, @RequestParam String kitaEndDate,
			@RequestParam String hasBreakfast, @RequestParam String hasLunch) {
		if (StringUtils.isEmpty(firstName)) {
			return new ControllerResponse(false, "Feld Vorname darf nicht leer sein!");
		}
		if (StringUtils.isEmpty(lastName)) {
			return new ControllerResponse(false, "Feld Nachname darf nicht leer sein!");
		}
		if (StringUtils.isEmpty(groupIdValue)) {
			return new ControllerResponse(false, "Ungültige Gruppen-Id. Kind kann keiner Gruppe zugewiesen werden!");
		}
		long groupId = Long.valueOf(groupIdValue);
		boolean breakfast = Boolean.valueOf(hasBreakfast);
		boolean lunch = Boolean.valueOf(hasLunch);
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);

		try {
			Date kitaStart = sdf.parse(kitaStartDate);
			Date kitaEnd = sdf.parse(kitaEndDate);
			if(!kitaStart.before(kitaEnd)) {
				return new ControllerResponse(false, "Das Startdatum muss vor dem Endedatum liegen!");
			}
			KindergartenChild newChild = new KindergartenChild(firstName, lastName, kitaStart, kitaEnd, breakfast,
					lunch);

			KindergartenGroup group = kindergartenGroupRepository.findOne(groupId);

			if (group != null) {
				newChild.setGroup(group);
			} else {
				return new ControllerResponse(false, "Es existiert keine Gruppe mit Id: " + groupIdValue);
			}
			
			Optional<KindergartenChild> alreadyExistingChild = kindergartenChildRepository.findByLastNameAndFirstName(lastName, firstName);
			if(alreadyExistingChild.isPresent()) {
				return new ControllerResponse(false, String.format("Kind kann nicht hinzugefügt werden. %s %s existiert bereits in der Datenbank!", firstName, lastName));
			}

			kindergartenChildRepository.save(newChild);
			
			// add todays meals for the new child if they have already been created
			List<Meal> todaysMeals = mealRepository.findByMealDate(PortalUtil.getToday());
			if(!todaysMeals.isEmpty() && PortalUtil.isTodayBetweenStartAndEnd(newChild.getKigaStart(), newChild.getKigaEnd())) {
				if(newChild.getBreakfast()) {
					Meal bf = new Meal(PortalUtil.getToday(), MealType.BREAKFAST, newChild);
					mealRepository.save(bf);
				}
				
				if(newChild.getLunch()) {
					Meal l = new Meal(PortalUtil.getToday(), MealType.LUNCH, newChild);
					mealRepository.save(l);
				}
			}
		} catch (Exception e) {
			return new ControllerResponse(false, "Kind konnte nicht hinzugefügt werden: " + e.getMessage());
		}

		return new ControllerResponse();
	}
}
