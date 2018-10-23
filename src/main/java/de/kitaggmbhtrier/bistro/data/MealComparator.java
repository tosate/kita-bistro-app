package de.kitaggmbhtrier.bistro.data;

import java.util.Comparator;

import de.kitaggmbhtrier.bistro.portal.util.PortalUtil;

public class MealComparator implements Comparator<Meal> {

	@Override
	public int compare(Meal meal1, Meal meal2) {
		return PortalUtil.compare(meal1.getChild(), meal2.getChild());
	}

}
