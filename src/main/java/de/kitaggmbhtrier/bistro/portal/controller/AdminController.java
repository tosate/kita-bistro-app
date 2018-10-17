package de.kitaggmbhtrier.bistro.portal.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import de.kitaggmbhtrier.bistro.data.KindergartenChild;

@Controller
public class AdminController {
	
	public static final String URL_ADMIN = "/admin";

	@RequestMapping(value=URL_ADMIN)
	public ModelAndView adminPage() {
		ModelAndView mav = new ModelAndView("admin");
		
		return mav;
	}
	
	public static String getNameList(List<KindergartenChild> children) {
		StringBuilder sb = new StringBuilder();
		for(KindergartenChild child : children) {
			sb.append(child.getFirstName());
			sb.append(" ");
			sb.append(child.getLastName());
			sb.append(", ");
		}
		String childrenList = sb.toString();
		
		return childrenList.substring(0, childrenList.length() - 2);
	}
}
