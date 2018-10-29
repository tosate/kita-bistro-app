package de.kitaggmbhtrier.bistro.portal.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.kitaggmbhtrier.bistro.data.KindergartenChild;
import de.kitaggmbhtrier.bistro.data.KindergartenGroup;
import de.kitaggmbhtrier.bistro.data.Meal;
import de.kitaggmbhtrier.bistro.data.MealComparator;
import de.kitaggmbhtrier.bistro.data.MealType;
import de.kitaggmbhtrier.bistro.portal.util.PortalUtil;
import de.kitaggmbhtrier.bistro.repository.KindergartenChildRepository;
import de.kitaggmbhtrier.bistro.repository.KindergartenGroupRepository;
import de.kitaggmbhtrier.bistro.repository.MealRepository;

@Controller
public class MealCheckController {

	public static final String URL_MEAL_CHECK = "/mealcheck";
	public static final String URL_SEARCH_JSON = URL_MEAL_CHECK + "/search";
	public static final String URL_UPDATE_MEAL = URL_MEAL_CHECK + "/update/meal";
	public static final String URL_SAVE_NOTICE = URL_MEAL_CHECK + "/save/notice";

	@Autowired
	private KindergartenGroupRepository kindergartenGroupRepository;
	@Autowired
	private MealRepository mealRepository;
	@Autowired
	private KindergartenChildRepository kindergartenChildRepository;

	@RequestMapping(URL_MEAL_CHECK)
	public ModelAndView mealCheckPage() throws JsonProcessingException {
		ModelAndView mav = new ModelAndView("mealcheck");

		Set<String> mealTypes = new HashSet<>();
		for (MealType mealType : MealType.values()) {
			mealTypes.add(mealType.getName());
		}

		mav.addObject("groupNames", (new ObjectMapper()).writer().writeValueAsString(this.getGroups()));
		mav.addObject("mealTypes", (new ObjectMapper()).writer().writeValueAsString(mealTypes));
		mav.addObject("meals", (new ObjectMapper()).writer().writeValueAsString(this.getTodaysMeals()));
		return mav;
	}

	@RequestMapping(value = URL_SEARCH_JSON, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Meal> listMeals(@RequestParam String groupName, @RequestParam String mealType) {
		List<Meal> result = new ArrayList<>();

		List<Meal> searchResult = this.mealRepository.findByMealDateAndType(PortalUtil.getToday(),
				MealType.findByName(mealType));

		// filter by group name
		for (Meal meal : searchResult) {
			if (meal.getChild().getGroup().getName().equals(groupName)) {
				result.add(meal);
			}
		}

		Collections.sort(result, new MealComparator());
		return result;
	}

	@RequestMapping(value = URL_UPDATE_MEAL, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ControllerResponse updateMeal(@RequestParam String mealId, @RequestParam String eaten) {
		try {
			Long id = Long.valueOf(mealId);
			Meal meal = this.mealRepository.findOne(id);
			if (meal != null) {
				meal.setEaten(!Boolean.valueOf(eaten));
				this.mealRepository.save(meal);
			} else {
				return new ControllerResponse(false,
						"Essen mit id=" + mealId + "konnte nicht in in der Datenbank gefunden werden.");
			}
		} catch (NumberFormatException e) {
			return new ControllerResponse(false, "Essensstatus konte nicht aktualisiert werden: " + e.getMessage());
		}

		return new ControllerResponse();
	}

	@RequestMapping(value = URL_SAVE_NOTICE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ControllerResponse saveNotice(@RequestParam String mealId, @RequestParam String notice) {
		try {
			Long id = Long.valueOf(mealId);
			Meal meal = this.mealRepository.findOne(id);
			if (meal != null) {
				meal.setNotice(notice);
				this.mealRepository.save(meal);
			} else {
				return new ControllerResponse(false,
						"Essen mit id=" + mealId + "konnte nicht in in der Datenbank gefunden werden.");
			}
		} catch (Exception e) {
			return new ControllerResponse(false, "Notiz konnte nicht zum Essen hinzugefügt werden: " + e.getMessage());
		}

		return new ControllerResponse();
	}

	private Set<String> getGroups() {
		Set<String> groups = new HashSet<>();
		for (KindergartenGroup group : kindergartenGroupRepository.findAll()) {
			groups.add(group.getName());
		}
		return groups;
	}

	private List<Meal> getTodaysMeals() {
		List<Meal> todaysMeals = mealRepository.findByMealDate(PortalUtil.getToday());

		if (todaysMeals.isEmpty()) {
			List<Meal> meals = new ArrayList<>();
			for (KindergartenChild child : kindergartenChildRepository.findAll()) {
				if (PortalUtil.isTodayBetweenStartAndEnd(child.getKigaStart(), child.getKigaEnd())) {
					if (child.getsBreakfast()) {
						Meal breakfast = new Meal(PortalUtil.getToday(), MealType.BREAKFAST, child);
						meals.add(breakfast);
					}
					if (child.getsLunch()) {
						Meal lunch = new Meal(PortalUtil.getToday(), MealType.LUNCH, child);
						meals.add(lunch);
					}
				}
			}
			mealRepository.save(meals);
			return meals;
		} else {
			return todaysMeals;
		}
	}
}
