package de.kitaggmbhtrier.bistro.portal.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import de.kitaggmbhtrier.bistro.data.Meal;
import de.kitaggmbhtrier.bistro.data.MealComparator;
import de.kitaggmbhtrier.bistro.repository.MealRepository;

@Controller
public class AnalysisController {
	public static final String URL_ANALYSIS = "/analysis";
	public static final String URL_MEALS_WITH_NOTICE = URL_ANALYSIS + "/meals-with-notice";
	
	@Autowired
	private MealRepository mealRepository;
	
	@RequestMapping(URL_ANALYSIS)
	public ModelAndView analysisPage() {
		ModelAndView mav = new ModelAndView("analysis");
		
		return mav;
	}
	
	@RequestMapping(value = URL_MEALS_WITH_NOTICE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Meal> getMealsWithNotice(@RequestParam Date date) {
		List<Meal> result = new ArrayList<>();
		
		List<Meal> searchResult = this.mealRepository.findByMealDate(this.removeTime(date));
		
		for(Meal meal : searchResult) {
			if(StringUtils.isNotEmpty(meal.getNotice())) {
				result.add(meal);
			}
		}
		
		Collections.sort(result, new MealComparator());
		return result;
	}
	
	private Date removeTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		return cal.getTime();
	}
}
