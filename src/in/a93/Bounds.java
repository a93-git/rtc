package in.a93;

import java.util.ArrayList;

public class Bounds extends Shape {
	public static final float DELTA = 0.00001f;
	private Point minExtent;
	private Point maxExtent;
	private ArrayList<Point> points;
	
	public Bounds() {
		// Empty box - has min at positive infinity and max at negative infinity
		// doesn't make sense now but helps when the first point is added
		// check updateBox method 
		// Also check http://www.raytracerchallenge.com/bonus/bounding-boxes.html
		this.maxExtent = new Point(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY);
		this.minExtent = new Point(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY);
		this.points = new ArrayList<Point>();
	}
	
	public Bounds(Point minPoint, Point maxPoint) {
		this.minExtent = minPoint;
		this.maxExtent = maxPoint;
		this.points = new ArrayList<Point>();
//		this.points.add(minPoint);
//		this.points.add(maxPoint);
	}

	public void addPoint(Point point) {
		this.points.add(point);
		this.updateBox(point);
	}
	
	private void updateBox(Point point) {
		if (point.getX() < this.getMinExtent().getX()) this.getMinExtent().setX(point.getX());
		if (point.getY() < this.getMinExtent().getY()) this.getMinExtent().setY(point.getY());
		if (point.getZ() < this.getMinExtent().getZ()) this.getMinExtent().setZ(point.getZ());

		if (point.getX() > this.getMaxExtent().getX()) this.getMaxExtent().setX(point.getX());
		if (point.getY() > this.getMaxExtent().getY()) this.getMaxExtent().setY(point.getY());
		if (point.getZ() > this.getMaxExtent().getZ()) this.getMaxExtent().setZ(point.getZ());
	}
	
	public Point getMinExtent() {
		return minExtent;
	}

	public void setMinExtent(Point minExtent) {
		this.minExtent = minExtent;
	}

	public Point getMaxExtent() {
		return maxExtent;
	}

	public void setMaxExtent(Point maxExtent) {
		this.maxExtent = maxExtent;
	}
	
	public void boundsOf(Shape shpae) {
		System.out.println("This was called");
	}
	
	public void boundsOf(Sphere sphere) {
		this.setMinExtent(new Point(-1, -1, -1));
		this.setMaxExtent(new Point(1, 1, 1));
	}
	
	public void boundsOf(Plane plane) {
		this.setMinExtent(new Point(Float.NEGATIVE_INFINITY, 0, Float.NEGATIVE_INFINITY));
		this.setMaxExtent(new Point(Float.POSITIVE_INFINITY, 0, Float.POSITIVE_INFINITY));
	}
	
	public void boundsOf(Cube cube) {
		this.setMinExtent(new Point(-1, -1, -1));
		this.setMaxExtent(new Point(1, 1, 1));
	}
	
	public void boundsOf(Cylinder cylinder) {
		this.setMinExtent(new Point(-1, cylinder.getMin(), -1));
		this.setMaxExtent(new Point(1, cylinder.getMax(), 1));
	}
	
	public void boundsOf(Cone cone) {
		float limit = Math.max(Math.abs(cone.getMax()), Math.abs(cone.getMin()));
		
		this.setMinExtent(new Point(-limit, cone.getMin(), -limit));
		this.setMaxExtent(new Point(limit, cone.getMax(), limit));
	}
	
	public void boundsOf(Triangle triangle) {
		this.addPoint(triangle.getP1());
		this.addPoint(triangle.getP2());
		this.addPoint(triangle.getP3());
	}
	
	public void add(Bounds bounds) {
		this.addPoint(bounds.getMaxExtent());
		this.addPoint(bounds.getMinExtent());
	}
	
	public boolean containsPoint(Point point) {
		return (this.getMaxExtent().getX() < point.getX() && this.getMinExtent().getX() > point.getX()) 
					|| (this.getMaxExtent().getX() - point.getX() < Bounds.DELTA && this.getMinExtent().getX() - point.getX() < Bounds.DELTA)
				&&  this.getMaxExtent().getY() < point.getY() && this.getMinExtent().getY() > point.getY() 
					|| (this.getMaxExtent().getY() - point.getY() < Bounds.DELTA && this.getMinExtent().getY() - point.getY() < Bounds.DELTA)
				&&  this.getMaxExtent().getZ() < point.getZ() && this.getMinExtent().getZ() > point.getZ() 
					|| (this.getMaxExtent().getZ() - point.getZ() < Bounds.DELTA && this.getMinExtent().getZ() - point.getZ() < Bounds.DELTA); 
	}
	
	public boolean containsBound(Bounds bound) {
		return this.containsPoint(bound.getMaxExtent()) 
				&& this.containsPoint(bound.getMinExtent());
	}

	public Bounds setTransformation(Matrix transformationMatrix) {
		Point[] points = new Point[8];
		points[0] = this.getMinExtent();
		points[1] = new Point(this.getMinExtent().getX(), this.getMinExtent().getY(), this.getMaxExtent().getZ());
		points[2] = new Point(this.getMinExtent().getX(), this.getMaxExtent().getY(), this.getMinExtent().getZ());
		points[3] = new Point(this.getMinExtent().getX(), this.getMaxExtent().getY(), this.getMaxExtent().getZ());
		points[4] = new Point(this.getMaxExtent().getX(), this.getMinExtent().getY(), this.getMinExtent().getZ());
		points[5] = new Point(this.getMaxExtent().getX(), this.getMinExtent().getY(), this.getMaxExtent().getZ());
		points[6] = new Point(this.getMaxExtent().getX(), this.getMaxExtent().getY(), this.getMinExtent().getZ());
		points[7] = this.getMaxExtent();
		
		Bounds newBox = new Bounds();
		
		for (Point p : points) {
			newBox.addPoint(Matrix.matrix2Point(Matrix.multiply(transformationMatrix, Matrix.tuple2Matrix(p))));
		}
		
		return newBox;
	}
	
	public Intersection[] rayIntersects(Ray ray) {
		
		try {
			float[] x = this.checkAxis(ray.getOrigin().getX(), ray.getDirection().getX(), this.getMinExtent().getX(), this.getMaxExtent().getX());
			float[] y = this.checkAxis(ray.getOrigin().getY(), ray.getDirection().getY(), this.getMinExtent().getY(), this.getMaxExtent().getY());
			float[] z = this.checkAxis(ray.getOrigin().getZ(), ray.getDirection().getZ(), this.getMinExtent().getZ(), this.getMaxExtent().getZ());
			float tmin = Math.max(Math.max(x[0], y[0]), z[0]);
			float tmax = Math.min(Math.min(x[1], y[1]), z[1]);

			Intersection[] result = null;
			
			if (tmin > tmax) return result;
			else { 
				result = Intersection.intersections(new Intersection(tmin, this), new Intersection(tmax, this));
				return result;
			}
		} catch (NullPointerException e) {
		
			return null;
		}
	}
	
	private float[] checkAxis(float origin, float direction, float min, float max) {
		float tminN = (min - origin);
		float tmaxN = (max - origin);
		
		float tmin, tmax;
		
		if (!(Math.abs(direction) > Bounds.DELTA)) direction += Bounds.DELTA;
		tmin = tminN / direction;
		tmax = tmaxN / direction;

		
		float[] result = new float[2];
		
		if (tmin > tmax) {
			float _t = tmax;
			tmax = tmin;
			tmin = _t;
		}
		result[0] = tmin; result[1] = tmax;
		return result;
	}

	@Override
	public Bounds parentSpaceBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Intersection[] intersect(Ray originalRay) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Vector localNormalAt(Point localPoint, Intersection intersection) {
		// TODO Auto-generated method stub
		return null;
	}
}
