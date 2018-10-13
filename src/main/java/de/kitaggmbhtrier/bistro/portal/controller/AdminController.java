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

import de.kitaggmbhtrier.bistro.data.KindergartenChild;
import de.kitaggmbhtrier.bistro.data.KindergartenGroup;
import de.kitaggmbhtrier.bistro.repository.KindergartenChildRepository;
import de.kitaggmbhtrier.bistro.repository.KindergartenGroupRepository;

@Controller
public class AdminController {
	
	public static final String URL_ADMIN = "/admin";
	public static final String URL_FETCH_GROUPS_JSON = URL_ADMIN + "/fetch/groups";
	public static final String URL_DELETE_GROUP_JSON = URL_ADMIN + "/delete/group";
	
	@Autowired
	private KindergartenGroupRepository kindergartenGroupRepository;
	@Autowired
	private KindergartenChildRepository kindergrtenChildRepository;

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
			KindergartenGroup group = kindergartenGroupRepository.findOne(id);
			List<KindergartenChild> childrenInGroup = kindergrtenChildRepository.findByGroupName(group.getName());
			if(childrenInGroup.size() == 0) {
				kindergartenGroupRepository.delete(id);
				return new ControllerResponse(true, "");
			} else {
				StringBuilder sb = new StringBuilder();
				for(KindergartenChild child : childrenInGroup) {
					sb.append(child.getFirstName());
					sb.append(" ");
					sb.append(child.getLastName());
					sb.append(", ");
				}
				String childrenList = sb.toString();
				return new ControllerResponse(false, "Gruppe kann nicht gelöscht werden, da ihr noch Kinder zugewiesen sind: " + childrenList.substring(0, childrenList.length() - 2));
			}
		} catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ControllerResponse(false, e.getMessage());
		}
	}
}
