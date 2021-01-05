package in.a93;

import in.a93.Exceptions.MatrixNotInvertibleException;

public class RenderPattern {
	public static final Color WHITE = new Color(1, 1, 1);
	public static final Color BLACK = new Color(0, 0, 0);
	
	private Color firstColor;
	private Color secondColor;
	private Matrix transform;
	
	public RenderPattern() {
		this.firstColor = RenderPattern.WHITE;
		this.secondColor = RenderPattern.BLACK;
		this.transform = Matrix.getIdentityMatrix(4, 4);
	}

	public RenderPattern(Color a, Color b) {
		this.firstColor = a;
		this.secondColor = b;
		this.transform = Matrix.getIdentityMatrix(4, 4);
	}
	
	public static RenderPattern getStripePattern(Color a, Color b) {
		return new RenderPattern(a, b);
	}
	
	public Color getStripeAtObject(Shape object, Point point) {
		Point worldToObjectPoint = getLocalPoint(object, point);
		Point objectToPatternPoint = getPatternPoint(worldToObjectPoint);
		
		return this.getStripeAt(objectToPatternPoint);
	}

	
	/*
	 * Private functions
	 */
	
	/*
	 * Color only changes on the x-axis, y and z have no effect
	 * 
	 * if (floor(x) % 2 == 0) return this.firstColor;
	 * else return this.secondColor
	 * 
	 */
	private Color getStripeAt(Point point) {
		if (Math.floor(point.getX()) % 2 == 0) return this.firstColor;
		else return this.secondColor;
	}
	
	private Point getPatternPoint(Point worldToObjectPoint) {
		try {
			return Matrix.matrix2Point(Matrix.multiply(Matrix.getInverseMatrix(this.getTransform()), Matrix.tuple2Matrix(worldToObjectPoint)));
		} catch (MatrixNotInvertibleException e) {
			e.printStackTrace();
			return null;
		}
	}

	private Point getLocalPoint(Shape object, Point point) {
		Point result = null;
		try {
			result = Matrix.matrix2Point(Matrix.multiply(Matrix.getInverseMatrix(object.getTransform()), Matrix.tuple2Matrix(point)));
		} catch (MatrixNotInvertibleException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/*
	 * Getters and setters
	 */
	
	public void setFirstColor(Color color) {
		this.firstColor = color;
	}

	public void setSecondColor(Color color) {
		this.secondColor = color;
	}
	
	public Color getFirstColor() {
		return this.firstColor;
	}
	
	public Color getSecondColor() {
		return this.secondColor;
	}
	
	public Matrix getTransform() {
		return transform;
	}

	public void setTransform(Matrix transform) {
		this.transform = transform;
	}

	@Override
	public String toString() {
		StringBuilder a = new StringBuilder();
		a.append(a.getClass().getName());
		a.append("\n" + this.firstColor);
		a.append("\n" + this.secondColor);
		
		return a.toString();
	}
}
