package in.a93;

public class RingPattern extends RenderPattern {

	public RingPattern() {
		super();
	}
	
	public RingPattern(Color a, Color b) {
		super(a, b);
	}

	@Override
	public Color getPatternAtShape(Shape shape, Point point) {
		Point worldToObjectPoint = super.getLocalPoint(shape, point);
		Point objectToPatternPoint = super.getPatternPoint(worldToObjectPoint);
		
		return this.getPatternAt(this, objectToPatternPoint);
	}
	
	private Color getPatternAt(RenderPattern pattern, Point objectToPatternPoint) {
		float xy = (float) Math.pow(objectToPatternPoint.getX(), 2) + (float) Math.pow(objectToPatternPoint.getZ(), 2);
		float xySqrt = (float) Math.sqrt(xy);
		
		if (Math.floor(xySqrt) % 2 == 0) return this.getFirstColor();
		else return this.getSecondColor();
	}
}
