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

import de.kitaggmbhtrier.bistro.data.KindergartenChild;
import de.kitaggmbhtrier.bistro.data.KindergartenGroup;
import de.kitaggmbhtrier.bistro.repository.KindergartenChildRepository;
import de.kitaggmbhtrier.bistro.repository.KindergartenGroupRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(AdminGroupController.class)
@AutoConfigureMockMvc(secure = false)
public class AdminGroupControllerTest {
	private static final String GROUP1_NAME = "group1";
	private static final String GROUP2_NAME = "group2";
	private static final String PARAM_GROUP_NAME = "groupName";
	private static final String PARAM_GROUP_ID = "groupId";
	@Autowired
	private MockMvc mvc;
	@MockBean
	private KindergartenGroupRepository kindergartenGroupRepository;
	@MockBean
	private KindergartenChildRepository kindergartenChildRepository;
	private List<KindergartenGroup> groups = new ArrayList<>();
	private KindergartenChild child1 = null;

	@Before
	public void init() {
		Calendar yesterday = Calendar.getInstance();
		yesterday.add(Calendar.DATE, -1);
		Calendar tomorrow = Calendar.getInstance();
		tomorrow.add(Calendar.DATE, 1);
		this.child1 = new KindergartenChild("John", "Doe", yesterday.getTime(), tomorrow.getTime(), true, true);
		KindergartenGroup group1 = new KindergartenGroup(GROUP1_NAME);
		group1.addChild(child1);
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

	@Test
	public void createGroupEmptyGroupName() throws Exception {
		this.mvc.perform(get(AdminGroupController.URL_CREATE_GROUP_JSON).param(PARAM_GROUP_NAME, "")
				.accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.success", equalTo(false)));
	}

	@Test
	public void deleteGroupInvalidGroupId() throws Exception {
		this.mvc.perform(get(AdminGroupController.URL_DELETE_GROUP_JSON).param(PARAM_GROUP_ID, "foo")
				.accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.success", equalTo(false)));
	}

	@Test
	public void deleteGroupContainingChildren() throws Exception {
		Long groupId = 42L;
		List<KindergartenChild> childrenInGroup = new ArrayList<>();
		childrenInGroup.add(child1);
		given(kindergartenGroupRepository.findOne(groupId)).willReturn(this.child1.getGroup());
		given(kindergartenChildRepository.findByGroupName(GROUP1_NAME)).willReturn(childrenInGroup);
		this.mvc.perform(get(AdminGroupController.URL_DELETE_GROUP_JSON).param(PARAM_GROUP_ID, String.valueOf(groupId))
				.accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.success", equalTo(false)));
	}

	@Test
	public void updateGroupEmptyGroupName() throws Exception {
		this.mvc.perform(get(AdminGroupController.URL_UPDATE_GROUP_JSON).param(PARAM_GROUP_ID, "42")
				.param(PARAM_GROUP_NAME, "").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.success", equalTo(false)));
	}

	@Test
	public void updateGroupInvalidGroupId() throws Exception {
		this.mvc.perform(get(AdminGroupController.URL_UPDATE_GROUP_JSON).param(PARAM_GROUP_ID, "foo")
				.param(PARAM_GROUP_NAME, GROUP1_NAME).accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.success", equalTo(false)));
	}
}
