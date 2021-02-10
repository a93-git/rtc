package in.a93.Demos;

import in.a93.*;

public class TriangleDemo {
	public void drawTriangles() {
		World w = new World();
		Group g = new Group();
		
		Triangle t = new Triangle(new Point(1, 1, 1), new Point(-1, -1, 1), new Point(1, -1, -1));
		Triangle t2 = new Triangle(new Point(1, 1, 1), new Point(-1, 1, -1), new Point(-1, -1, 1));
		t2.getMaterial().setTransparency(1);
		t2.getMaterial().setColor(new Color(1, 0, 0));;
		Triangle t3 = new Triangle(new Point(1, 1, 1), new Point(-1, 1, -1), new Point(1, -1, -1));
		t3.getMaterial().setColor(new Color(1, 0.3f, 0.45f));;
		Triangle t4 = new Triangle(new Point(-1, -1, 1), new Point(1, -1, -1), new Point(-1, 1, -1));
		t4.getMaterial().setColor(new Color(0, 0.4f, 1));;
		
		g.addChild(t);
		g.addChild(t2);
		g.addChild(t3);
		g.addChild(t4);
		g.setTransform(g.getTransform().translate(0, 1, 0));
		
		
		Plane p = new Plane();
		
		p.setTransform(p.getTransform().translate(0, -1, 0));
		
//		w.addObjects(t);
//		w.addObjects(t2);
//		w.addObjects(t3);
//		w.addObjects(t4);
		w.addObjects(g);
		w.addObjects(p);
		
		Camera camera = new Camera(720, 480, (float) Math.PI / 3);
		camera.setTransform(Matrix.multiply(camera.getTransform(), Matrix.getViewTransform(new Point(0, 1.5f, -5), new Point(0, 1, 0), new Vector(0, 1, 0))));
		Canvas canvas = camera.render(w, 0);
		
		String ppmData = Canvas.canvasToPpm(canvas);
		Save.saveToDiskAsText("E:\\triangle.ppm", ppmData);
	}
}
