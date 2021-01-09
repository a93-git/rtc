package in.a93.Demos;

import in.a93.*;

public class ConeCylinderDemo {
	public void drawScene(String filePath) {
		World w = new World();
		
		Plane p = new Plane();
		w.addObjects(p);
		
		Cone c = new Cone();
		w.addObjects(c);
		
		c.setTransform(c.getTransform().rotateX(-(float) Math.PI / 4));
		c.setCapped(true);
		c.setMin(0);
		c.setMax(2);
		c.getMaterial().setPattern(new RingPattern(new Color(0.8f, 0.3f, 0.4f), new Color(1, 0, 0.2f)));
		c.getMaterial().getPattern().setTransform(Matrix.getIdentityMatrix(4, 4).scale(0.25f, 0.25f, 0.25f));
		Camera camera = new Camera(360, 240, (float) Math.PI / 1.5f);
		camera.setTransform(Matrix.multiply(camera.getTransform(), Matrix.getViewTransform(new Point(0, 1.5f, -5), new Point(0, 1, 0), new Vector(0, 1, 0))));
		
		Canvas canvas = camera.render(w, 5);
		String ppmData = Canvas.canvasToPpm(canvas);
		
		Save.saveToDiskAsText(filePath, ppmData);
		
	}
}
