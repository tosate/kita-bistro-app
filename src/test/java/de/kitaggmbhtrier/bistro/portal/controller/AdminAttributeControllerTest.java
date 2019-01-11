package de.kitaggmbhtrier.bistro.portal.controller;

import static org.mockito.BDDMockito.given;
import static org.hamcrest.Matchers.hasSize;
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

import de.kitaggmbhtrier.bistro.data.ChildAttribute;
import de.kitaggmbhtrier.bistro.repository.ChildAttributeRepository;
import de.kitaggmbhtrier.bistro.repository.KindergartenChildRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(AdminAttributeController.class)
@AutoConfigureMockMvc(secure = false)
public class AdminAttributeControllerTest {

	private static final String ATTRIB_CLASSIFICATION1 = "classification1";
	private static final String ATTRIB_NAME1 = "name1";
	private static final String ATTRIB_CLASSIFICATION2 = "classification2";
	private static final String ATTRIB_NAME2 = "name2";
	@Autowired
	private MockMvc mvc;
	@MockBean
	private ChildAttributeRepository childAttributeRepository;
	@MockBean
	private KindergartenChildRepository kindergartenChildRepository;
	private List<ChildAttribute> attributes = new ArrayList<>();
	
	@Before
	public void init() {
		ChildAttribute attribute1 = new ChildAttribute(ATTRIB_CLASSIFICATION1, ATTRIB_NAME1);
		attributes.add(attribute1);
		ChildAttribute attribute2 = new ChildAttribute(ATTRIB_CLASSIFICATION2, ATTRIB_NAME2);
		attributes.add(attribute2);
	}
	
	@Test
	public void fetchAttributes() throws Exception {
		given(this.childAttributeRepository.findAll()).willReturn(this.attributes);
		this.mvc.perform(get(AdminAttributeController.URL_FETCH_ATTRIBUTES_JSON).accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$", hasSize(2)));
	}
}
