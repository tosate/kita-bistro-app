package de.kitaggmbhtrier.bistro.portal.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import de.kitaggmbhtrier.bistro.data.KindergartenChild;
import de.kitaggmbhtrier.bistro.data.KindergartenChildComparator;
import de.kitaggmbhtrier.bistro.repository.KindergartenChildRepository;

@Controller
public class AdminChildrenController {

	@Autowired
	private KindergartenChildRepository kindergartenChildRepository;
	
	public static final String URL_FETCH_CHILDREN_JSON = AdminController.URL_ADMIN + "/fetch/children";
	
	@RequestMapping(value = URL_FETCH_CHILDREN_JSON, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<KindergartenChild> fetchChildren() {
		List<KindergartenChild> result = new ArrayList<>();
		Iterator<KindergartenChild> childrenIterator = childrenIterator = kindergartenChildRepository.findAll().iterator();
		while(childrenIterator.hasNext()) {
			result.add(childrenIterator.next());
		}
		Collections.sort(result, new KindergartenChildComparator());
		
		return result;
	}
}
