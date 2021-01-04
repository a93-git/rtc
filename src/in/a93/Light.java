package in.a93;

public class Light {
	private Color intensity;
	private Point position;
	
	public Light(Point position, Color intensity) {
		this.intensity = intensity;
		this.position = position;
	}

	public Color getIntensity() {
		return intensity;
	}

	public void setIntensity(Color intensity) {
		this.intensity = intensity;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(this.getIntensity());
		s.append("\n" + this.getPosition());
		return s.toString();
	}
}
