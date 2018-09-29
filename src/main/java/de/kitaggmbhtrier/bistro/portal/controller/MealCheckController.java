package de.kitaggmbhtrier.bistro.portal.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class MealCheckController {

	public static final String URL_MEAL_CHECK = "/mealcheck";

	@RequestMapping(URL_MEAL_CHECK)
	public ModelAndView mealCheckPage() throws JsonProcessingException {
		ModelAndView mav = new ModelAndView("mealcheck");
		
		Set<String> groups = new HashSet<>();
		groups.add("Rote Gruppe");
		groups.add("Blaue Gruppe");
		groups.add("Grüne Gruppe");
		
		Set<String> meals = new HashSet<>();
		meals.add("Frühstück");
		meals.add("Mittagessen");
		
		mav.addObject("groupNames", (new ObjectMapper()).writer().writeValueAsString(groups));
		mav.addObject("meals", (new ObjectMapper()).writer().writeValueAsString(meals));
		return mav;
	}
}
