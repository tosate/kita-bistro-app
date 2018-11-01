package de.kitaggmbhtrier.bistro.data;

import java.util.Comparator;

public class KindergartenGroupComparator implements Comparator<KindergartenGroup> {

	@Override
	public int compare(KindergartenGroup g1, KindergartenGroup g2) {
		return g1.getName().compareTo(g2.getName());
	}

}
