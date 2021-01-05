package in.a93;

public class CheckerPattern extends RenderPattern {

	public CheckerPattern() {
		super();
	}
	
	public CheckerPattern(Color a, Color b) {
		super(a, b);
	}

	@Override
	public Color getPatternAtShape(Shape shape, Point point) {
		Point worldToObjectPoint = super.getLocalPoint(shape, point);
		Point objectToPatternPoint = super.getPatternPoint(worldToObjectPoint);
		
		return this.getPatternAt(this, objectToPatternPoint);
	}

	private Color getPatternAt(RenderPattern pattern, Point objectToPatternPoint) {
		float xyzSum = (float) (Math.floor(objectToPatternPoint.getX()) + Math.floor(objectToPatternPoint.getY()) + Math.floor(objectToPatternPoint.getZ()));
		
		if (xyzSum % 2 == 0) return this.getFirstColor();
		else return this.getSecondColor();
	}
}
