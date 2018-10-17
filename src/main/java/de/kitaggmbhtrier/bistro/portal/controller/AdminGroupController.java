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

import de.kitaggmbhtrier.bistro.data.KindergartenChild;
import de.kitaggmbhtrier.bistro.data.KindergartenGroup;
import de.kitaggmbhtrier.bistro.repository.KindergartenChildRepository;
import de.kitaggmbhtrier.bistro.repository.KindergartenGroupRepository;

@Controller
public class AdminGroupController {
	
	@Autowired
	private KindergartenGroupRepository kindergartenGroupRepository;
	@Autowired
	private KindergartenChildRepository kindergrtenChildRepository;

	public static final String URL_FETCH_GROUPS_JSON = AdminController.URL_ADMIN + "/fetch/groups";
	public static final String URL_CREATE_GROUP_JSON = AdminController.URL_ADMIN + "/create/group";
	public static final String URL_DELETE_GROUP_JSON = AdminController.URL_ADMIN + "/delete/group";
	public static final String URL_UPDATE_GROUP_JSON = AdminController.URL_ADMIN + "/update/group";
	
	@RequestMapping(value = URL_FETCH_GROUPS_JSON, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<KindergartenGroup> fetchGroups() {
		List<KindergartenGroup> result = new ArrayList<>();
		Iterator<KindergartenGroup> groupIterator = this.kindergartenGroupRepository.findAll().iterator();
		while(groupIterator.hasNext()) {
			result.add(groupIterator.next());
		}
		return result;
	}
	
	@RequestMapping(value = URL_CREATE_GROUP_JSON, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ControllerResponse createGroup(@RequestParam String groupName) {
		if(groupName == null || groupName.length() == 0) {
			return new ControllerResponse(false, "Ungültiger Gruppenname!");
		}
		
		KindergartenGroup newGroup = new KindergartenGroup(groupName);
		kindergartenGroupRepository.save(newGroup);
		return new ControllerResponse();
	}
	
	@RequestMapping(value = URL_DELETE_GROUP_JSON, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ControllerResponse deleteGroup(@RequestParam String groupId) {
		
		try {
			long id = Long.valueOf(groupId);
			KindergartenGroup group = kindergartenGroupRepository.findOne(id);
			List<KindergartenChild> childrenInGroup = kindergrtenChildRepository.findByGroupName(group.getName());
			if(childrenInGroup.size() == 0) {
				kindergartenGroupRepository.delete(id);
				return new ControllerResponse();
			} else {
				return new ControllerResponse(false, "Gruppe kann nicht gelöscht werden, da ihr noch Kinder zugewiesen sind: " + AdminController.getNameList(childrenInGroup));
			}
		} catch(Exception e) {
			return new ControllerResponse(false, e.getMessage());
		}
	}
	
	@RequestMapping(value = URL_UPDATE_GROUP_JSON, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ControllerResponse updateGroup(@RequestParam String groupId, @RequestParam String groupName) {
		if(groupName == null || groupName.length() == 0) {
			return new ControllerResponse(false, "Ungültiger Gruppenname");
		}
		
		try {
			long id = Long.valueOf(groupId);
			KindergartenGroup group = kindergartenGroupRepository.findOne(id);
			if(group != null) {
				group.setName(groupName);
				kindergartenGroupRepository.save(group);
				return new ControllerResponse();
			} else {
				return new ControllerResponse(false, "Gruppe wurde nicht gefunden!");
			}
		} catch(Exception e) {
			return new ControllerResponse(false, e.getMessage());
		}
	}
}
