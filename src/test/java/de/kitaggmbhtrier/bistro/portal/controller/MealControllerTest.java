package de.kitaggmbhtrier.bistro.portal.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

@RunWith(SpringRunner.class)
@WebMvcTest(MealCheckController.class)
@Import(WebSecurityConfig.class)
public class MealControllerTest {

	private static final String PARAM_GROUP_NAME = "groupName";
	private static final String PARAM_MEAL_TYPE = "mealType";
	private static final String PARAM_HIDE_EATEN = "hideEaten";
	private static final String PARAM_MEAL_ID = "mealId";
	private static final String GROUP_NAME = "group";
	private static final String PARAM_EATEN = "eaten";
	private static final String PARAM_NOTICE = "notice";
	@Autowired
	private MockMvc mvc;
	@MockBean
	private KindergartenGroupRepository kindergartenGroupRepository;
	@MockBean
	private MealRepository mealRepository;
	@MockBean
	private KindergartenChildRepository kindergartenChildRepository;
	private List<Meal> listBreakfastResult = new ArrayList<>();
	private List<Meal> listLunchResult = new ArrayList<>();
	private List<KindergartenGroup> groups = new ArrayList<>();

	@Before
	public void init() {
		Calendar yesterday = Calendar.getInstance();
		yesterday.add(Calendar.DATE, -1);
		Calendar tomorrow = Calendar.getInstance();
		tomorrow.add(Calendar.DATE, 1);
		KindergartenGroup group = new KindergartenGroup(GROUP_NAME);
		KindergartenChild child1 = new KindergartenChild("John", "Doe", yesterday.getTime(), tomorrow.getTime(), true,
				true);
		child1.setGroup(group);
		KindergartenChild child2 = new KindergartenChild("Jane", "Doe", yesterday.getTime(), tomorrow.getTime(), true,
				false);
		child2.setGroup(group);
		KindergartenChild child3 = new KindergartenChild("Chuck", "Tester", yesterday.getTime(), tomorrow.getTime(),
				false, true);
		child3.setGroup(group);
		Meal meal1 = new Meal(new Date(), MealType.BREAKFAST, child1);
		Meal meal2 = new Meal(new Date(), MealType.LUNCH, child1);
		Meal meal3 = new Meal(new Date(), MealType.BREAKFAST, child2);
		meal3.setEaten(true);
		Meal meal4 = new Meal(new Date(), MealType.LUNCH, child3);
		meal4.setEaten(true);
		this.listBreakfastResult.add(meal1);
		this.listBreakfastResult.add(meal3);
		this.listLunchResult.add(meal2);
		this.listLunchResult.add(meal4);
		this.groups.add(group);
	}

	@Test
	public void listBreakfastMeals() throws Exception {
		given(this.mealRepository.findByMealDateAndType(PortalUtil.getToday(), MealType.BREAKFAST))
				.willReturn(listBreakfastResult);
		this.mvc.perform(get(MealCheckController.URL_SEARCH_JSON).param(PARAM_GROUP_NAME, GROUP_NAME)
				.param(PARAM_MEAL_TYPE, MealType.BREAKFAST.getName()).param(PARAM_HIDE_EATEN, String.valueOf(false))
				.accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].type", equalTo(MealType.BREAKFAST.toString())))
				.andExpect(jsonPath("$[0].child.breakfast", equalTo(true)));
	}

	@Test
	public void listLunchMealHideEaten() throws Exception {
		given(this.mealRepository.findByMealDateAndType(PortalUtil.getToday(), MealType.LUNCH))
				.willReturn(listLunchResult);
		this.mvc.perform(get(MealCheckController.URL_SEARCH_JSON).param(PARAM_GROUP_NAME, GROUP_NAME)
				.param(PARAM_MEAL_TYPE, MealType.LUNCH.getName()).param(PARAM_HIDE_EATEN, String.valueOf(true))
				.accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].type", equalTo(MealType.LUNCH.toString())))
				.andExpect(jsonPath("$[0].child.lunch", equalTo(true)));
	}

	@Test
	public void updateMeal() throws Exception {
		given(this.mealRepository.findOne(1L)).willReturn(listLunchResult.get(0));
		this.mvc.perform(get(MealCheckController.URL_UPDATE_MEAL).param(PARAM_MEAL_ID, "1")
				.param(PARAM_EATEN, String.valueOf(true)).accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.success", equalTo(true)));
	}

	@Test
	public void saveNotice() throws Exception {
		given(this.mealRepository.findOne(1L)).willReturn(listLunchResult.get(0));
		this.mvc.perform(get(MealCheckController.URL_SAVE_NOTICE).param(PARAM_MEAL_ID, "1")
				.param(PARAM_NOTICE, "notcie").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.success", equalTo(true)));
	}
}
