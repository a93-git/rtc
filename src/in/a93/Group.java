package in.a93;

import java.util.ArrayList;
import java.util.UUID;

public class Group extends Shape {
	private String uuid;
	private ArrayList<Shape> children = new ArrayList<Shape>();
	
	public Group() {
		super();
		this.uuid = UUID.randomUUID().toString();
	}
	
	public ArrayList<Shape> getChildren() {
		return this.children;
	}

	public void addChild(Shape child) {
		this.children.add(child);
		child.setParent(this);
	}

	@Override
	public String getUuid() { return uuid; }

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Group: " + this.uuid);
		s.append("\nChildren:");
		if (this.getChildren() == null) s.append(" null");
		else {
			for (Shape i : this.getChildren()) {
				s.append("\n" + i);
			}
		}
		
		return s.toString();
	}
	
	@Override
	public Intersection[] intersect(Ray originalRay) {
		Ray localRay = super.getLocalRay(this, originalRay);
		return this.localIntersect(localRay);		
	}

	private Intersection[] localIntersect(Ray localRay) {
		Intersection[] intersections = null;
		// If there are no children, return null
		if (this.getChildren().size() == 0) {
			return intersections;
		}
		
		ArrayList<Intersection> temp = new ArrayList<Intersection>();
		
		for (Shape s : this.getChildren()) {
			Intersection[] t = s.intersect(localRay);
			if (!(t == null)) { // If there are no intersections, skip it
				for (Intersection i : t) {
					temp.add(i);
				}
			}
		}
		
		// Sort the resulting intersections by their T value
		temp.sort(new IntersectionComparator());
		
		intersections = new Intersection[temp.size()];
		
		for (int i = 0; i < temp.size(); i++) {
			intersections[i] = temp.get(i);
		}
		
		return intersections;
	}

	@Override
	public Vector normalAt(Point point) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Vector localNormalAt(Point localPoint) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
