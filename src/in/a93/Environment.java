package in.a93;

public class Environment {
	private Tuple gravity;
	private Tuple wind;
	
	public Environment(Tuple gravity, Tuple wind) {
		this.gravity = gravity;
		this.wind = wind;
	}
	
	public void setGravity(Tuple gravity) {
		this.gravity = gravity;
	}
	
	public Tuple getGravity() {
		return this.gravity;
	}
	
	public void setWind(Tuple wind) {
		this.wind= wind;
	}
	
	public Tuple getWind() {
		return this.wind;
	}
}
