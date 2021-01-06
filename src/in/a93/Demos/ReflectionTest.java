package in.a93.Demos;

import in.a93.*;

public class ReflectionTest {
	private static Color wallColor = new Color(1, 0.9f, 0.9f);
	
	public void drawSpheresOnPlane(String absolutePath) {
		World w = new World();
		Plane p = new Plane();
		Sphere s = new Sphere();
		
		p.getMaterial().setReflective(1);
		p.getMaterial().setColor(wallColor);
		
		s.getMaterial().setReflective(0.01f);
		s.getMaterial().setColor(new Color(1, 0, 0));
		s.setTransform(s.getTransform().translate(0, 1, 0));
		
		w.addObjects(p);
		w.addObjects(s);
		
		Camera c = new Camera(720, 480, (float) Math.PI / 3);
		c.setTransform(Matrix.multiply(c.getTransform(), Matrix.getViewTransform(new Point(0, 1.5f, -5), new Point(0, 1, 0), new Vector(0, 1, 0))));
		Canvas canvas = c.render(w, 4);
		
		
		String ppmData = Canvas.canvasToPpm(canvas);
		Save.saveToDiskAsText(absolutePath, ppmData);
	}
}