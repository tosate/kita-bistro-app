package de.kitaggmbhtrier.bistro.data;

import java.util.Comparator;

public class KindergartenChildComparator implements Comparator<KindergartenChild> {

	@Override
	public int compare(KindergartenChild child1, KindergartenChild child2) {
		return child1.getLastName().compareTo(child2.getLastName());
	}

}
