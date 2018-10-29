package de.kitaggmbhtrier.bistro.portal.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import de.kitaggmbhtrier.bistro.data.ChildAttribute;
import de.kitaggmbhtrier.bistro.data.KindergartenChild;
import de.kitaggmbhtrier.bistro.repository.ChildAttributeRepository;
import de.kitaggmbhtrier.bistro.repository.KindergartenChildRepository;

@Controller
public class AdminAttributeController {
	
	@Autowired
	private ChildAttributeRepository childAttributeRepository;
	@Autowired
	private KindergartenChildRepository kindergartenChildRepository;
	
	public static final String URL_FETCH_ATTRIBUTES_JSON = AdminController.URL_ADMIN + "/fetch/attributes";
	public static final String URL_CREATE_ATTRIBUTE_JSON = AdminController.URL_ADMIN + "/create/attribute";
	public static final String URL_DELETE_ATTRIBUTE_JSON = AdminController.URL_ADMIN + "/delete/attribute";
	public static final String URL_UPDATE_ATTRIBUTE_JSON = AdminController.URL_ADMIN + "/update/attribute";
	
	@RequestMapping(value = URL_FETCH_ATTRIBUTES_JSON, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ChildAttribute> fetchAttributes() {
		List<ChildAttribute> result = new ArrayList<>();
		Iterator<ChildAttribute> attributeIterator = this.childAttributeRepository.findAll().iterator();
		while(attributeIterator.hasNext()) {
			result.add(attributeIterator.next());
		}
		return result;
	}
	
	@RequestMapping(value = URL_CREATE_ATTRIBUTE_JSON, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ControllerResponse createAttribute(@RequestParam String attributeClassification, @RequestParam String attributeName) {
		if(StringUtils.isEmpty(attributeClassification)) {
			return new ControllerResponse(false, "Ungültige Kategorie für Besonderheit!");
		}
		
		if(StringUtils.isEmpty(attributeName)) {
			return new ControllerResponse(false, "Ungültiger Name für Besonderheit!");
		}
		ChildAttribute newAttribute = new ChildAttribute(attributeClassification, attributeName);
		childAttributeRepository.save(newAttribute);
		return new ControllerResponse();
	}
	
	@RequestMapping(value = URL_DELETE_ATTRIBUTE_JSON, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ControllerResponse deleteAttribute(@RequestParam String attributeId) {
		try {
			long id = Long.valueOf(attributeId);
			ChildAttribute attribute = childAttributeRepository.findOne(id);
			List<KindergartenChild> childrenWithAttribute = kindergartenChildRepository.findByAttributes_Name(attribute.getName());
			if(childrenWithAttribute.size() == 0) {
				childAttributeRepository.delete(attribute);
				return new ControllerResponse();
			} else {
				return new ControllerResponse(false, "Besonderheit kann nicht gelöscht werden, da sie noch Kindern zugewiesen ist: " + AdminController.getNameList(childrenWithAttribute));
			}
		} catch(Exception e) {
			return new ControllerResponse(false, e.getMessage());
		}
	}
	
	@RequestMapping(value = URL_UPDATE_ATTRIBUTE_JSON, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ControllerResponse updateAttribute(@RequestParam String attributeId, @RequestParam String attributeClassification, @RequestParam String attributeName) {
		if(StringUtils.isEmpty(attributeClassification)) {
			return new ControllerResponse(false, "Ungültige Kategorie für Besonderheit!");
		}
		
		if(StringUtils.isEmpty(attributeName)) {
			return new ControllerResponse(false, "Ungültiger Name für Besonderheit!");
		}
		
		try {
			long id = Long.valueOf(attributeId);
			ChildAttribute attribute = childAttributeRepository.findOne(id);
			if(attribute != null) {
				attribute.setClassification(attributeClassification);
				attribute.setName(attributeName);
				childAttributeRepository.save(attribute);
				return new ControllerResponse();
			} else {
				return new ControllerResponse(false, "Besonderheit konnte nicht gefunden werden!");
			}
		} catch(Exception e) {
			return new ControllerResponse(false, e.getMessage());
		}
	}
}
