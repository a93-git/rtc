package in.a93;

public class Color extends Tuple {
	public Color(float red, float green, float blue) {
		super(red, green, blue, 0.0f);
	}

	public float getRed() { return this.getX(); }
	public float getGreen() { return this.getY(); }
	public float getBlue() { return this.getZ(); }
	
	public static Color schurProduct(Color c1, Color c2) {
		return new Color(
				c1.getRed() * c2.getRed(),
				c1.getGreen()* c2.getGreen(),
				c1.getBlue() * c2.getBlue()
				);
	}
}
