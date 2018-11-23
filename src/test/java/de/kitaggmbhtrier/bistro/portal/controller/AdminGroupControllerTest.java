package de.kitaggmbhtrier.bistro.portal.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import de.kitaggmbhtrier.bistro.data.KindergartenGroup;
import de.kitaggmbhtrier.bistro.repository.KindergartenChildRepository;
import de.kitaggmbhtrier.bistro.repository.KindergartenGroupRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(AdminGroupController.class)
@AutoConfigureMockMvc(secure=false)
public class AdminGroupControllerTest {
	private static final String GROUP1_NAME = "group1";
	private static final String GROUP2_NAME = "group2";
	@Autowired
	private MockMvc mvc;
	@MockBean
	private KindergartenGroupRepository kindergartenGroupRepository;
	@MockBean
	private KindergartenChildRepository kindergartenChildRepository;
	private List<KindergartenGroup> groups = new ArrayList<>();

	@Before
	public void init() {
		KindergartenGroup group1 = new KindergartenGroup(GROUP1_NAME);
		groups.add(group1);
		KindergartenGroup group2 = new KindergartenGroup(GROUP2_NAME);
		groups.add(group2);
	}

	@Test
	public void fetchGroups() throws Exception {
		given(this.kindergartenGroupRepository.findAll()).willReturn(groups);
		this.mvc.perform(get(AdminGroupController.URL_FETCH_GROUPS_JSON).accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$", hasSize(2)));
	}
}
