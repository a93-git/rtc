package in.a93;

public class Color extends Tuple {
	public static final Color BLACK = new Color(0, 0, 0);
	public static final Color WHITE = new Color(1, 1, 1);
	
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
	
	@Override
	public Color scalarMultiply(float x) {
		return new Color(this.getX() * x, this.getY() * x, this.getZ() * x);
	}
	
	public Color add(Color other) {
		return new Color(this.getRed() + other.getRed(), this.getGreen() + other.getGreen(), this.getBlue() + other.getBlue());
	}
	
	public Color subtract(Color other) {
		return new Color(this.getX() - other.getX(), this.getY() - other.getY(), this.getZ() - other.getZ());
	}
	
	public void setBlack() {
		this.setX(0);
		this.setY(0);
		this.setZ(0);
	}
}
