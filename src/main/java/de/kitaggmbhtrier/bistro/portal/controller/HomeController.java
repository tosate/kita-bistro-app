package de.kitaggmbhtrier.bistro.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeController {

	@RequestMapping("/")
	public RedirectView redirectView(RedirectAttributes attributes) {
		return new RedirectView(MealCheckController.URL_MEAL_CHECK);
	}
}
