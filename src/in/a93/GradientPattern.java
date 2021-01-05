package in.a93;

public class GradientPattern extends RenderPattern {

	public GradientPattern() {
		super();
	}
	
	public GradientPattern(Color a, Color b) {
		super(a, b);
	}

	@Override
	public Color getPatternAtShape(Shape shape, Point point) {
		Point worldToObjectPoint = super.getLocalPoint(shape, point);
		Point objectToPatternPoint = super.getPatternPoint(worldToObjectPoint);
		
		return this.getPatternAt(this, objectToPatternPoint);
	}

	private Color getPatternAt(RenderPattern pattern, Point objectToPatternPoint) {
		System.out.println("Correct function called");
		Color colorDiff = this.getSecondColor().subtract((this.getFirstColor()));
		float xDiff = objectToPatternPoint.getX() - (float) Math.floor(objectToPatternPoint.getX());
		Color c = colorDiff.scalarMultiply(xDiff);
		Color blendedColor = this.getFirstColor().add(c);
		
		return blendedColor;
	}

}
