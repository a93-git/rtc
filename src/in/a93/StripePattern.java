package in.a93;

public class StripePattern extends RenderPattern {

	public StripePattern() {
		super();
	}
	
	public StripePattern(Color a, Color b) {
		super(a, b);
	}
	
	public static StripePattern getStripePattern(Color a, Color b) {
		return new StripePattern(a, b);
	}
	
	@Override
	public Color getPatternAtShape(Shape shape, Point point) {
		Point worldToObjectPoint = super.getLocalPoint(shape, point);
		Point objectToPatternPoint = super.getPatternPoint(worldToObjectPoint);
		
		return this.getPatternAt(this, objectToPatternPoint);
	}
	
	private Color getPatternAt(RenderPattern pattern, Point point) {
		if (Math.floor(point.getX()) % 2 == 0) return this.getFirstColor();
		else return this.getSecondColor();
	}

}
