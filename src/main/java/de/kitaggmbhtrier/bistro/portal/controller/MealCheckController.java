package de.kitaggmbhtrier.bistro.portal.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
import de.kitaggmbhtrier.bistro.repository.MealTypeRepository;

@Controller
public class MealCheckController {

	public static final String URL_MEAL_CHECK = "/mealcheck";
	
	@Autowired
	private MealTypeRepository mealTypeRepository;
	@Autowired
	private KindergartenGroupRepository kindergartenGroupRepository;
	@Autowired
	private MealRepository mealRepository;
	@Autowired
	private KindergartenChildRepository kindergartenChildRepository;
	private MealType breakfastType;
	private MealType lunchType;

	@RequestMapping(URL_MEAL_CHECK)
	public ModelAndView mealCheckPage() throws JsonProcessingException {
		ModelAndView mav = new ModelAndView("mealcheck");
		
		mav.addObject("groupNames", (new ObjectMapper()).writer().writeValueAsString(this.getGroups()));
		mav.addObject("mealTypes", (new ObjectMapper()).writer().writeValueAsString(this.getMealTypes()));
		mav.addObject("meals", (new ObjectMapper()).writer().writeValueAsString(this.getTodaysMeals()));
		return mav;
	}
	
	private Set<String> getGroups() {
		Set<String> groups = new HashSet<>();
		for(KindergartenGroup group : kindergartenGroupRepository.findAll()) {
			groups.add(group.getName());
		}
		return groups;
	}
	
	private Set<String> getMealTypes() {
		Set<String> mealTypes = new HashSet<>();
		for(MealType mealType : mealTypeRepository.findAll()) {
			mealTypes.add(mealType.getName());
			if(mealType.getName().startsWith("Fr")) {
				this.breakfastType = mealType;
			}
			if(mealType.getName().startsWith("Mi")) {
				this.lunchType = mealType;
			}
		}
		return mealTypes;
	}
	
	private List<Meal> getTodaysMeals() {
		Calendar today = new GregorianCalendar();
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MILLISECOND, 0);
		List<Meal> todaysMeals = mealRepository.findByMealDate(today.getTime());
		
		if(todaysMeals.isEmpty()) {
			List<Meal> meals = new ArrayList<>();
			for(KindergartenChild child : kindergartenChildRepository.findAll()) {
				if(child.getsBreakfast()) {
					Meal breakfast = new Meal(today.getTime(), breakfastType, child);
					meals.add(breakfast);
				}
				if(child.getsLunch()) {
					Meal lunch = new Meal(today.getTime(), lunchType, child);
					meals.add(lunch);
				}
			}
			mealRepository.save(meals);
			return meals;
		} else {
			return todaysMeals;
		}
	}
}
