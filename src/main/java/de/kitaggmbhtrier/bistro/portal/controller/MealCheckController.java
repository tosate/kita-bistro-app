package de.kitaggmbhtrier.bistro.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MealCheckController {

	public static final String URL_MEAL_CHECK = "/mealcheck";

	@RequestMapping(URL_MEAL_CHECK)
	public ModelAndView mealCheckPage() {
		ModelAndView mav = new ModelAndView("mealcheck");
		return mav;
	}
}
