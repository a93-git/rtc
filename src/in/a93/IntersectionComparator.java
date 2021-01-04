package in.a93;

import java.util.Comparator;

class IntersectionComparator implements Comparator<Intersection> {
	@Override
	public int compare(Intersection o1, Intersection o2) {
		return Double.compare(o1.getT(), o2.getT());
	}
}
