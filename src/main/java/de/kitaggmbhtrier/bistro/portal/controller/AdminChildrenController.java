package de.kitaggmbhtrier.bistro.portal.controller;

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
	public static final String URL_CREATE_CHILD_JSON = AdminController.URL_ADMIN + "/save/child";

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
	public @ResponseBody ControllerResponse saveChild(@RequestParam boolean updateMode, @RequestParam long childId,
			@RequestParam String firstName, @RequestParam String lastName, @RequestParam long groupId,
			@RequestParam long kitaStartLong, @RequestParam long kitaEndLong, @RequestParam String hasBreakfast,
			@RequestParam String hasLunch) {
		if (StringUtils.isEmpty(firstName)) {
			return new ControllerResponse(false, "Feld Vorname darf nicht leer sein!");
		}
		if (StringUtils.isEmpty(lastName)) {
			return new ControllerResponse(false, "Feld Nachname darf nicht leer sein!");
		}
		Date kitaStart = new Date(kitaStartLong);
		Date kitaEnd = new Date(kitaEndLong);
		if (!kitaStart.before(kitaEnd)) {
			return new ControllerResponse(false, "Das Startdatum muss vor dem Endedatum liegen!");
		}
		boolean breakfast = Boolean.valueOf(hasBreakfast);
		boolean lunch = Boolean.valueOf(hasLunch);

		if (updateMode) {
			return this.updateChild(childId, firstName, lastName, groupId, kitaStart, kitaEnd, breakfast, lunch);
		} else {
			return this.createChild(firstName, lastName, groupId, kitaStart, kitaEnd, breakfast, lunch);
		}
	}

	private ControllerResponse createChild(String firstName, String lastName, long groupId, Date kitaStart,
			Date kitaEnd, boolean breakfast, boolean lunch) {
		try {
			KindergartenChild newChild = new KindergartenChild(firstName, lastName, kitaStart, kitaEnd, breakfast,
					lunch);

			ControllerResponse setGroupResponse = this.setGroup(newChild, groupId);
			if(!setGroupResponse.isSuccess()) {
				return setGroupResponse;
			}

			Optional<KindergartenChild> alreadyExistingChild = kindergartenChildRepository
					.findByLastNameAndFirstName(lastName, firstName);
			if (alreadyExistingChild.isPresent()) {
				return new ControllerResponse(false,
						String.format("Kind kann nicht hinzugefügt werden. %s %s existiert bereits in der Datenbank!",
								firstName, lastName));
			}

			kindergartenChildRepository.save(newChild);

			// add todays meals for the new child if they have already been created
			List<Meal> todaysMeals = mealRepository.findByMealDate(PortalUtil.getToday());
			if (!todaysMeals.isEmpty()
					&& PortalUtil.isTodayBetweenStartAndEnd(newChild.getKitaStart(), newChild.getKitaEnd())) {
				PortalUtil.createTodaysMeals(mealRepository, newChild);
			}
		} catch (Exception e) {
			return new ControllerResponse(false, "Kind konnte nicht hinzugefügt werden: " + e.getMessage());
		}

		return new ControllerResponse();
	}

	private ControllerResponse updateChild(long childId, String firstName, String lastName, long groupId, Date kitaStart, Date kitaEnd, boolean breakfast, boolean lunch) {
		try {
			KindergartenChild existingChild = kindergartenChildRepository.findOne(childId);
			if(existingChild == null) {
				return new ControllerResponse(false, String.format("Kind mit id=%d konnte nicht aktualisiert werden, da es nicht in der Datenbank gefunden wurde!", childId));
			}
			existingChild.setFirstName(firstName);
			existingChild.setLastName(lastName);
			
			ControllerResponse setGroupResponse = this.setGroup(existingChild, groupId);
			if(!setGroupResponse.isSuccess()) {
				return setGroupResponse;
			}
			existingChild.setKitaStart(kitaStart);
			existingChild.setKitaEnd(kitaEnd);
			existingChild.setBreakfast(breakfast);
			existingChild.setLunch(lunch);
			
			kindergartenChildRepository.save(existingChild);
			
			List<Meal> todaysMeals = mealRepository.findByMealDate(PortalUtil.getToday());
			// delete meals for existing child
			for(Meal meal : todaysMeals) {
				if(meal.getChild().equals(existingChild)) {
					mealRepository.delete(meal.getId());
				}
			}
			if (!todaysMeals.isEmpty()
					&& PortalUtil.isTodayBetweenStartAndEnd(existingChild.getKitaStart(), existingChild.getKitaEnd())) {
				PortalUtil.createTodaysMeals(mealRepository, existingChild);
			}
		} catch(Exception e) {
			return new ControllerResponse(false, "Kind konnte nicht aktualisiert werden: " + e.getMessage());
		}
		return new ControllerResponse();
	}
	
	private ControllerResponse setGroup(KindergartenChild child, long groupId) {
		KindergartenGroup group = kindergartenGroupRepository.findOne(groupId);

		if (group != null) {
			child.setGroup(group);
			return new ControllerResponse();
		} else {
			return new ControllerResponse(false, "Es existiert keine Gruppe mit Id: " + groupId);
		}
	}
}
