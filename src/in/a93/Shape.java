package in.a93;

import in.a93.Exceptions.MatrixNotInvertibleException;

public abstract class Shape {
	private Matrix transform;
	private Material material;
	private Group parent;
	private Matrix inverse;
	private Matrix transformTransposeInverse;
	
//	private static float DELTA = 0.00001f;
	
	public Shape() {
		this.transform = Matrix.getIdentityMatrix(4, 4);
		this.material = new Material();
		this.parent = null;
	}

	/*
	 * Converts the ray from world space to object space
	 * The converted ray is then consumed by the individual shapes
	 */
	public Ray getLocalRay(Shape shape, Ray originalRay) {
		Ray ray = null;

		try {
			Matrix invTransform = null;
			if (Matrix.inverseMatMap.containsKey(shape.getTransform())) invTransform = Matrix.inverseMatMap.get(shape.getTransform());
			else invTransform = Matrix.getInverseMatrix(shape.getTransform());
			ray = Ray.transformRay(originalRay, invTransform);
		} 
		catch (MatrixNotInvertibleException e) {
			e.printStackTrace();
		}
		
		return ray;
	}
	
	public Point getLocalPoint(Point point, Matrix invTransform) {
		Point localPoint = null;
		
		try {	
			if (Matrix.inverseMatMap.containsKey(this.getTransform())) invTransform = Matrix.inverseMatMap.get(this.getTransform());
			else invTransform = Matrix.getInverseMatrix(this.getTransform());
		} catch (MatrixNotInvertibleException e) {
			e.printStackTrace();
			return localPoint;
		}
		
//		invTransform = this.getTransformInverse();
		
		localPoint = Matrix.matrix2Point(Matrix.multiply(invTransform, Matrix.tuple2Matrix(point)));
		
		return localPoint;
	}
	
	public Point worldToObject(Point point) {
		if (!(this.getParent() == null)) {
			point = this.getParent().worldToObject(point);
		}
		
		Matrix invTransform = null;
		
		try {	
			if (Matrix.inverseMatMap.containsKey(this.getTransform())) invTransform = Matrix.inverseMatMap.get(this.getTransform());
			else invTransform = Matrix.getInverseMatrix(this.getTransform());
		} catch (MatrixNotInvertibleException e) {
			e.printStackTrace();
			return null; // will break if it is ever returned
		}
		
//		invTransform = this.getTransformInverse();
		
		return Matrix.matrix2Point(Matrix.multiply(invTransform, Matrix.tuple2Matrix(point)));
	}
	
	public Vector normalToWorld(Vector normal) {		
		Matrix invTransform = null;
		
		try {	
			if (Matrix.inverseMatMap.containsKey(this.getTransform())) invTransform = Matrix.inverseMatMap.get(this.getTransform());
			else invTransform = Matrix.getInverseMatrix(this.getTransform());
		} catch (MatrixNotInvertibleException e) {
			e.printStackTrace();
			return null; // will break if it is ever returned
		}
		
//		invTransform = this.getTransformInverse();
		
		normal = Matrix.matrix2Vector(Matrix.multiply(Matrix.transpose(invTransform), Matrix.tuple2Matrix(normal)));
		normal.setW(0);
		normal = Vector.normalize(normal);
		
		if (!(this.getParent() == null)) {
			normal = this.getParent().normalToWorld(normal);
		}
		
		return normal;
	}
	
	public Vector normalAt(Point point, Intersection intersection) {
		Point localPoint = this.worldToObject(point);
		Vector localNormal = this.localNormalAt(localPoint, intersection); 
		return this.normalToWorld(localNormal);
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
	
	public abstract Bounds parentSpaceBounds();

	/*
	 * Getters and setters
	 */
	public Matrix getTransform() { return this.transform; }
	public void setTransform(Matrix transform) { this.transform = transform; }

	public Material getMaterial() { return this.material; }
	public void setMaterial(Material material) { this.material = material; }
	public String getUuid() { return "";}
	public Group getParent() { return parent; }
	public void setParent(Group parent) { this.parent = parent;	}
	public Matrix getTransformInverse() { return this.inverse; }
	public Matrix getTransformTransposeInverse() { return this.transformTransposeInverse; }
	
	
	/*
	 * Abstract methods
	 */
	public abstract Intersection[] intersect(Ray originalRay);
	protected abstract Vector localNormalAt(Point localPoint, Intersection intersection);

}
