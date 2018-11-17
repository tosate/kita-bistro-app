package de.kitaggmbhtrier.bistro.portal.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import de.kitaggmbhtrier.bistro.data.KindergartenChild;
import de.kitaggmbhtrier.bistro.data.KindergartenGroup;
import de.kitaggmbhtrier.bistro.data.Meal;
import de.kitaggmbhtrier.bistro.data.MealType;
import de.kitaggmbhtrier.bistro.portal.config.WebSecurityConfig;
import de.kitaggmbhtrier.bistro.portal.util.PortalUtil;
import de.kitaggmbhtrier.bistro.repository.KindergartenChildRepository;
import de.kitaggmbhtrier.bistro.repository.KindergartenGroupRepository;
import de.kitaggmbhtrier.bistro.repository.MealRepository;
import de.kitaggmbhtrier.bistro.portal.controller.MealCheckController;

@RunWith(SpringRunner.class)
@WebMvcTest(MealCheckController.class)
@Import(WebSecurityConfig.class)
public class MealControllerTest {

	@Autowired
	private MockMvc mvc;
	@MockBean
	private KindergartenGroupRepository kindergartenGroupRepository;
	@MockBean
	private MealRepository mealRepository;
	@MockBean
	private KindergartenChildRepository kindergartenChildRepository;
	private List<Meal> listMealsResult = new ArrayList<>();
	
	@Before
	public void init() {
		Calendar yesterday = Calendar.getInstance();
		yesterday.add(Calendar.DATE, -1);
		Calendar tomorrow = Calendar.getInstance();
		tomorrow.add(Calendar.DATE, 1);
		KindergartenGroup group = new KindergartenGroup("group");
		KindergartenChild child = new KindergartenChild("John", "Doe", yesterday.getTime(), tomorrow.getTime(), true, true);
		child.setGroup(group);
		Meal meal = new Meal(new Date(), MealType.BREAKFAST, child);
		this.listMealsResult.add(meal);
	}
	
	@Test
	public void listMeals() throws Exception {
		given(this.mealRepository.findByMealDateAndType(PortalUtil.getToday(), MealType.BREAKFAST)).willReturn(listMealsResult);
		this.mvc.perform(
				get("/mealcheck/search")
				.param("groupName", "group")
				.param("mealType", MealType.BREAKFAST.getName())
				.param("hideEaten", String.valueOf(false))
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk());
	}
}
