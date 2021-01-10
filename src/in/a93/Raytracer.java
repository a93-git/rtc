package in.a93;

import in.a93.Demos.GroupDemo;
import in.a93.*;

public class Raytracer {
	public static void main(String[] args) {
		GroupDemo a = new GroupDemo();
		a.drawScene("E:\\group_demo.ppm");
		
//		Plane p = new Plane();
//		Cylinder c = new Cylinder();
//		
//		c.setMin(0);
//		c.setMax(1);
////		c.setCapped(true);
//		
//		World w = new World();
//		w.addObjects(c);
//		w.addObjects(p);
//		
//		Camera camera = new Camera(180, 120, (float) Math.PI / 1.75f);
//		camera.setTransform(Matrix.multiply(camera.getTransform(), Matrix.getViewTransform(new Point(0, 1.75f, -5), new Point(0, 1, 0), new Vector(0, 1, 0))));
//		Canvas canvas = camera.render(w, 5);
//		String ppmData = Canvas.canvasToPpm(canvas);
//		
//		Save.saveToDiskAsText("E:\\test2.ppm", ppmData);
		
	}
}
