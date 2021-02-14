package in.a93.Demos;

import in.a93.*;

import java.nio.file.Path;

public class TriangleDemo2 {
	public static void drawWorld(String absolutePath) {
		Path filepath = Path.of("C:\\Users\\win10\\Desktop\\teapot-low.obj");
		World w = new World();
		
		
		ObjFile o = new ObjFile(filepath);
		System.out.println("Parsing obj file");
		o.parseObjFile();
		
		System.out.println("Done parsing obj file");
		
		Group g = o.objectToGroup();
		g.setTransform(g.getTransform().scale(0.33f, 0.33f, 0.33f).rotateX((float) Math.PI / -2).translate(0, -0.3f, 0));
		g.getMaterial().setColor(new Color(0.75f, 0.6f, 0.2f));
		
		w.addObjects(g);
		
		Plane floor = new Plane();
		floor.getMaterial().setPattern(new CheckerPattern(new Color(1, 1, 1), new Color(0.6f, 0.6f, 0.6f)));
		w.addObjects(floor);
		
		Plane backWall = new Plane();
		backWall.getMaterial().setPattern(new CheckerPattern(new Color(1, 1, 1), new Color(0.6f, 0.6f, 0.6f)));
		backWall.setTransform(backWall.getTransform().rotateX((float) Math.PI / 2).translate(0, 0, 5));
		w.addObjects(backWall);
		
		Camera camera = new Camera(720, 480, (float) Math.PI / 1.4f);
		camera.setTransform(Matrix.multiply(camera.getTransform(), Matrix.getViewTransform(new Point(0, 5.5f, -5), new Point(0, 1, 0), new Vector(0, 1, 0))));
		
		
		System.out.println("Rendering world");
		Canvas canvas = camera.render(w, 0);
		
		String ppmData = Canvas.canvasToPpm(canvas);
		Save.saveToDiskAsText(absolutePath, ppmData);
	}
}
