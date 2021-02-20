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
		if (this.parentSpaceBounds().rayIntersects(originalRay) == null) {
			return null;
		} else {
			Ray localRay = super.getLocalRay(this, originalRay);
			return this.localIntersect(localRay);					
		}
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
	public Vector normalAt(Point point, Intersection intersection) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Vector localNormalAt(Point localPoint, Intersection intersection) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Bounds parentSpaceBounds() {
		Bounds bounds = new Bounds();
		for (int i = 0; i < this.getChildren().size(); i++) {
			Bounds temp = this.getChildren().get(i).parentSpaceBounds();
			if (
				 	   temp.getMaxExtent().getX() == Float.NEGATIVE_INFINITY   
					|| temp.getMaxExtent().getY() == Float.NEGATIVE_INFINITY   
					|| temp.getMaxExtent().getZ() == Float.NEGATIVE_INFINITY   
					|| temp.getMinExtent().getX() == Float.POSITIVE_INFINITY
					|| temp.getMinExtent().getY() == Float.POSITIVE_INFINITY
					|| temp.getMinExtent().getZ() == Float.POSITIVE_INFINITY
				) {
				continue;
			}
			bounds.add(this.getChildren().get(i).parentSpaceBounds());
//			System.out.println("x is: " + bounds.getMaxExtent().getX() + " for i: " + i);
		}
		return bounds;		
	}

	@Override
	public Matrix getTransformInverse() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
