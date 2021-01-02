package in.a93.Tests;

import in.a93.Environment;
import in.a93.Projectile;
import in.a93.Tuple;

public class ProjectileTest {
	
	public static Projectile tick(Environment e, Projectile p) {
		Tuple newPos = p.getPosition().add(p.getVelocity());
		Tuple newVel = p.getVelocity().add(e.getGravity()).add(e.getWind());
		
		return new Projectile(newPos, newVel);
	}
}
