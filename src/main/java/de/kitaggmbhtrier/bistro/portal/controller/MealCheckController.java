package de.kitaggmbhtrier.bistro.portal.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import de.kitaggmbhtrier.bistro.data.MealType;
import de.kitaggmbhtrier.bistro.repository.KindergartenChildRepository;
import de.kitaggmbhtrier.bistro.repository.KindergartenGroupRepository;
import de.kitaggmbhtrier.bistro.repository.MealRepository;

@Controller
public class MealCheckController {

	public static final String URL_MEAL_CHECK = "/mealcheck";
	public static final String URL_SEARCH_JSON = URL_MEAL_CHECK + "/search";
	
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
		for(MealType mealType : MealType.values()) {
			mealTypes.add(mealType.getName());
		}
		
		mav.addObject("groupNames", (new ObjectMapper()).writer().writeValueAsString(this.getGroups()));
		mav.addObject("mealTypes", (new ObjectMapper()).writer().writeValueAsString(mealTypes));
		mav.addObject("meals", (new ObjectMapper()).writer().writeValueAsString(this.getTodaysMeals()));
		return mav;
	}
	
	@RequestMapping(value = URL_SEARCH_JSON, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Meal> listMeals(
			@RequestParam String groupName, @RequestParam String mealType) {
		List<Meal> result = new ArrayList<>();
		
		result = this.mealRepository.findByMealDateAndGroupNameAndType(getToday(), groupName, MealType.findByName(mealType));
		/*
		 * Retrive meals from DB
		 */
		
		return result;
	}
	
	private Set<String> getGroups() {
		Set<String> groups = new HashSet<>();
		for(KindergartenGroup group : kindergartenGroupRepository.findAll()) {
			groups.add(group.getName());
		}
		return groups;
	}
	
	private List<Meal> getTodaysMeals() {
		List<Meal> todaysMeals = mealRepository.findByMealDate(getToday());
		
		if(todaysMeals.isEmpty()) {
			List<Meal> meals = new ArrayList<>();
			for(KindergartenChild child : kindergartenChildRepository.findAll()) {
				if(child.getsBreakfast()) {
					Meal breakfast = new Meal(getToday(), MealType.BREAKFAST, child);
					meals.add(breakfast);
				}
				if(child.getsLunch()) {
					Meal lunch = new Meal(getToday(), MealType.LUNCH, child);
					meals.add(lunch);
				}
			}
			mealRepository.save(meals);
			return meals;
		} else {
			return todaysMeals;
		}
	}
	
	private Date getToday() {
		Calendar today = new GregorianCalendar();
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MILLISECOND, 0);
		
		return today.getTime();
	}
}
