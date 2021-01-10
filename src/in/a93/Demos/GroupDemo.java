package in.a93.Demos;

import in.a93.*;

public class GroupDemo {
	public void drawScene(String filePath) {
		Group hexagon = hexagonGroup();
		World w = new World();
		w.addObjects(hexagon);
		
		Camera c = new Camera(720, 480, (float) Math.PI / 2.75f);
		c.setTransform(Matrix.multiply(c.getTransform(), Matrix.getViewTransform(new Point(0, 1.75f, -3), new Point(0, 1, 0), new Vector(0, 1, 0)).rotateX((float) Math.PI / 9)));
		
		Canvas canvas = c.render(w, 5);
		String ppmData = Canvas.canvasToPpm(canvas);
		
		Save.saveToDiskAsText(filePath, ppmData);
	}
	
	private Sphere hexagonCorner() {
		Sphere s = new Sphere();
		s.setTransform(s.getTransform().scale(0.25f, 0.25f, 0.25f).translate(0, 0, -1));
		return s;
	}
	
	private Cylinder hexagonEdge() {
		Cylinder c = new Cylinder();
		c.setMin(0);
		c.setMax(1);
		c.setCapped(true);
		c.setTransform(c.getTransform().scale(0.25f, 1, 0.25f).rotateZ(-(float) Math.PI / 2).rotateY(-(float) Math.PI / 6).translate(0, 0, -1));
		return c;
	}
	
	private Group hexagonSide() {
		Group g = new Group();
		g.addChild(hexagonCorner());
		g.addChild(hexagonEdge());
		
		return g;
	}
	
	private Group hexagonGroup() {
		Group g = new Group();
		
		for (int n = 0; n < 6; n++) {
			Group side = hexagonSide();
			side.setTransform(side.getTransform().rotateY(n * (float) Math.PI / 3));
			g.addChild(side);
		}
		
		return g;
	}
}
