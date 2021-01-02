package in.a93;

public class Projectile {
	private Tuple position;
	private Tuple velocity;
	
	public Projectile(Tuple position, Tuple velocity) {
		this.position = position;
		this.velocity = velocity;
	}
	
	public void setPosition(Tuple position) {
		this.position = position;
	}
	
	public Tuple getPosition() {
		return this.position;
	}
	
	public void setVelocity(Tuple velocity) {
		this.velocity = velocity;
	}
	
	public Tuple getVelocity() {
		return this.velocity;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Position: " + this.getPosition().magnitude());
		s.append(" Velocity: " + this.getVelocity().magnitude());
		return s.toString();
	}
	
}
