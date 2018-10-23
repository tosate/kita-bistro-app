package de.kitaggmbhtrier.bistro.data;

import java.util.Comparator;

import de.kitaggmbhtrier.bistro.portal.util.PortalUtil;

public class KindergartenChildComparator implements Comparator<KindergartenChild> {

	@Override
	public int compare(KindergartenChild child1, KindergartenChild child2) {
		return PortalUtil.compare(child1, child2);
	}

}
