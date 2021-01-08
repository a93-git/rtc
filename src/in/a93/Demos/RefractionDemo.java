package in.a93.Demos;

import in.a93.Plane;
import in.a93.*;

public class RefractionDemo {
	
	public void drawScene(String absolutePath) {
		World w = new World();
		
		Plane floor = new Plane();
		floor.getMaterial().setPattern(new CheckerPattern(new Color(0, 0, 0), new Color(1, 1, 1)));
//		floor.getMaterial().getPattern().setTransform(Matrix.getIdentityMatrix(4, 4).scale(0.2f, 0.2f, 0.2f));;
		
		Plane wall = new Plane();
		wall.getMaterial().setPattern(new CheckerPattern(new Color(0, 0, 0), new Color(1, 1, 1)));
		wall.setTransform(wall.getTransform().rotateX((float) Math.PI / 2).translate(0, 0, 5));
//		wall.getMaterial().getPattern().setTransform(Matrix.getIdentityMatrix(4, 4).scale(0.2f, 0.2f, 0.2f));;
		
		Sphere glassBall = Sphere.getGlassSphere();
		glassBall.getMaterial().setDiffuse(0);
		glassBall.getMaterial().setAmbient(0);
		glassBall.getMaterial().setReflective(1);
		glassBall.getMaterial().setShininess(300);
		glassBall.getMaterial().setSpecular(1);
//		glassBall.getMaterial().setColor(new Color(1, 0, 0));
		glassBall.setTransform(glassBall.getTransform().translate(0, 1, 0));
		
		Sphere ball = new Sphere();
		ball.getMaterial().setColor(new Color(1, 0, 0));
		ball.getMaterial().setSpecular(0);
		ball.setTransform(ball.getTransform().scale(0.3f, 0.3f, 0.3f).translate(0, 1, 3));
		
		Sphere bubble = Sphere.getGlassSphere();
		bubble.getMaterial().setRefractiveIndex(1);
		bubble.setTransform(ball.getTransform().scale(0.25f, 0.25f, 0.25f).translate(0, 1, 0));
		bubble.getMaterial().setDiffuse(0);
		bubble.getMaterial().setAmbient(0);
		bubble.getMaterial().setReflective(1);
		bubble.getMaterial().setShininess(300);
		bubble.getMaterial().setSpecular(1);
		
		w.addObjects(floor);
		w.addObjects(glassBall);
		w.addObjects(wall);
		w.addObjects(ball);
		w.addObjects(bubble);
		Camera c = new Camera(720, 480, (float) Math.PI / 1.75f);
		c.setTransform(Matrix.multiply(c.getTransform(), Matrix.getViewTransform(new Point(0, 1.5f, -5), new Point(0, 1, 0), new Vector(0, 1, 0))));
		
		Canvas canvas = c.render(w, 5);
		String ppmData = Canvas.canvasToPpm(canvas);
		
		Save.saveToDiskAsText(absolutePath, ppmData);
	}

}
