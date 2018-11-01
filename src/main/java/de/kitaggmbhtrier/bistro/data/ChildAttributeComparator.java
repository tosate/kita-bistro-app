package de.kitaggmbhtrier.bistro.data;

import java.util.Comparator;

public class ChildAttributeComparator implements Comparator<ChildAttribute> {

	@Override
	public int compare(ChildAttribute a1, ChildAttribute a2) {
		if(a1.getClassification().equals(a2.getClassification())) {
			return a1.getName().compareTo(a2.getName());
		} else {
			return a1.getClassification().compareTo(a2.getClassification());
		}
	}

}
