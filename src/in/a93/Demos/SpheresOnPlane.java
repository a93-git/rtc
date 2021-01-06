package in.a93.Demos;

import in.a93.*;

public class SpheresOnPlane {
	private static Color wallColor = new Color(1, 0.9f, 0.9f);
	
	public void drawSpheresOnPlane(String absolutePath) {
		World world = new World();
		Light light = new Light(new Point(-10, 10, -10), new Color(1, 1, 1));		

		world.setLight(0, light);
		world.addObjects(floor()); 
		world.addObjects(leftWall()); 
		world.addObjects(rightWall()); 
		world.addObjects(backWall()); 
		world.addObjects(ceiling()); 
		world.addObjects(ball()); 
		world.addObjects(ball2()); 

		Camera camera = new Camera(720, 480, (float) Math.PI / 1.75f);
		
		camera.setTransform(Matrix.multiply(camera.getTransform(), Matrix.getViewTransform(new Point(0, 1.8f, -5), new Point(0, 1, 0), new Vector(0, 1, 0))));
		camera.setTransform(camera.getTransform().rotateX((float) Math.PI / -12)); // shows the ceiling
		
		Canvas canvas = camera.render(world, 0);
		
		String ppmData = Canvas.canvasToPpm(canvas);
		Save.saveToDiskAsText(absolutePath, ppmData);
		
	}
	
	private static Plane floor() {
		Plane floor = new Plane();

		floor.getMaterial().setColor(new Color(1, 0.9f, 0.9f));
		floor.getMaterial().setSpecular(0);
		floor.setTransform(floor.getTransform().rotateY((float) Math.PI / 2));
		
		return floor;
	}
	
	private static Plane ceiling() {
		Plane ceiling = new Plane();
		
		ceiling.setTransform(ceiling.getTransform().translate(0, 12, 0));
		ceiling.getMaterial().setColor(SpheresOnPlane.wallColor);
//		ceiling.getMaterial().setShininess(0);
		ceiling.getMaterial().setSpecular(0);
		return ceiling;
	}
	
	private static Plane leftWall() {
		Plane leftWall = new Plane();
		
		leftWall.setTransform(leftWall.getTransform().rotateZ((float) Math.PI / -2).translate(-15, 0, 0));
		leftWall.getMaterial().setColor(SpheresOnPlane.wallColor);
		leftWall.getMaterial().setSpecular(0);
		return leftWall;
	}
	
	private static Plane rightWall() {
		Plane rightWall = new Plane();
		rightWall.setTransform(Matrix.getIdentityMatrix(4, 4).rotateZ((float) (Math.PI/2)).translate(10, 0, 0));
		rightWall.getMaterial().setColor(SpheresOnPlane.wallColor);
		rightWall.getMaterial().setSpecular(0);
		
		return rightWall;
	}

	private static Plane backWall() {
		Plane backWall = new Plane();
		backWall.setTransform(backWall.getTransform().rotateX((float) Math.PI / 2).translate(0, 0, 15));
		backWall.getMaterial().setColor(SpheresOnPlane.wallColor);
		backWall.getMaterial().setSpecular(0);
		
		return backWall;
	}
	
	private static Sphere ball() {
		Sphere ball = new Sphere();
		ball.setTransform(ball.getTransform().translate(0, 1, 0));
		ball.getMaterial().setColor(new Color(1, 0, 0));
//		ball.getMaterial().setSpecular(0);
		
		return ball;
	}
	
	private static Sphere ball2() {
		Sphere ball = new Sphere();
		ball.setTransform(ball.getTransform().translate(-5, 1, 5));
		ball.getMaterial().setColor(new Color(1, 0, 1));
//		ball.getMaterial().setSpecular(0);
		
		return ball;
	}
}

