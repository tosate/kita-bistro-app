package de.kitaggmbhtrier.bistro.portal.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import de.kitaggmbhtrier.bistro.data.KindergartenGroup;
import de.kitaggmbhtrier.bistro.repository.KindergartenGroupRepository;

@Controller
public class AdminController {
	
	public static final String URL_ADMIN = "/admin";
	public static final String URL_FETCH_GROUPS_JSON = URL_ADMIN + "/fetch/groups";
	public static final String URL_DELETE_GROUP_JSON = URL_ADMIN + "/delete/group";
	
	@Autowired
	private KindergartenGroupRepository kindergartenGroupRepository;

	@RequestMapping(value=URL_ADMIN)
	public ModelAndView adminPage() {
		ModelAndView mav = new ModelAndView("admin");
		
		return mav;
	}
	
	@RequestMapping(value = URL_FETCH_GROUPS_JSON, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<KindergartenGroup> fetchGroups() {
		List<KindergartenGroup> result = new ArrayList<>();
		Iterator<KindergartenGroup> groupIterator = this.kindergartenGroupRepository.findAll().iterator();
		while(groupIterator.hasNext()) {
			result.add(groupIterator.next());
		}
		return result;
	}
	
	@RequestMapping(value = URL_DELETE_GROUP_JSON, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ControllerResponse deleteGroup(@RequestParam String groupId) {
		
		try {
			long id = Long.valueOf(groupId);
			// TODO check whether group is empty
			kindergartenGroupRepository.delete(id);
			return new ControllerResponse(true, "");
		} catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ControllerResponse(false, e.getMessage());
		}
	}
}
