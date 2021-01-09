package in.a93;

import in.a93.Exceptions.MatrixNotInvertibleException;

public abstract class Shape {
	private Matrix transform;
	private Material material;
	
//	private static float DELTA = 0.00001f;
	
	public Shape() {
		this.transform = Matrix.getIdentityMatrix(4, 4);
		this.material = new Material();
	}

	/*
	 * Converts the ray from world space to object space
	 * The converted ray is then consumed by the individual shapes
	 */
	public Ray getLocalRay(Shape shape, Ray originalRay) {
		Ray ray = null;
		try {
			ray = Ray.transformRay(originalRay, Matrix.getInverseMatrix(shape.getTransform()));
		} catch (MatrixNotInvertibleException e) {
			e.printStackTrace();
		}
		
		return ray;
	}
	
	public Point getLocalPoint(Point point, Matrix invTransform) {
		Point localPoint = null;
		
		try {	
			invTransform = Matrix.getInverseMatrix(this.getTransform());
		} catch (MatrixNotInvertibleException e) {
			e.printStackTrace();
			return localPoint;
		}
		
		localPoint = Matrix.matrix2Point(Matrix.multiply(invTransform, Matrix.tuple2Matrix(point)));
		
		return localPoint;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		
		Shape other = (Shape) obj;
		
		return this.getTransform().equals(other.getTransform())
			   && this.getMaterial().equals(other.getMaterial());
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Transform: \n" + this.getTransform());
		s.append("Material: \n" + this.getMaterial());
		
		return s.toString();
	}
	/*
	 * Getters and setters
	 */
	public Matrix getTransform() { return this.transform; }
	public void setTransform(Matrix transform) { this.transform = transform; }
	public Material getMaterial() { return this.material; }
	public void setMaterial(Material material) { this.material = material; }
	public String getUuid() { return "";}
	
	/*
	 * Abstract methods
	 */
	public abstract Intersection[] intersect(Ray originalRay);
	public abstract Vector normalAt(Point point);
}
