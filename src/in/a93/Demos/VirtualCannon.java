package in.a93.Demos;

import in.a93.Canvas;
import in.a93.Color;
import in.a93.Environment;
import in.a93.Point;
import in.a93.Projectile;
import in.a93.Save;
import in.a93.Vector;
import in.a93.Tests.ProjectileTest;

public class VirtualCannon {
	public static void virtualCannon(String absoluteFilePath) {
		// Virtual cannon
		Canvas canvas = new Canvas(900, 550);
		Color color = new Color(1.0f, 1.0f, 1.0f);
		Projectile p = new Projectile(new Point(0.0f, 1.0f, 0.0f), Vector.normalize(new Vector(1.0f, 1.8f, 0.0f)).scalarMultiply(11.25f));
		Environment e = new Environment(new Vector(0.0f, -0.1f, 0.0f), new Vector(-0.01f, 0.0f, 0.0f));
		
		int tickCount = 0;
		while (!(p.getPosition().getY() < 0)) {
//			Canvas.colorSquare(canvas, (int) p.getPosition().getX(), (int) (canvas.getCanvasHeight() - p.getPosition().getY()), color);
			canvas.setColorAt((int) p.getPosition().getX(), (int) (canvas.getCanvasHeight() - p.getPosition().getY()), color);
			p = ProjectileTest.tick(e, p);
			tickCount++;
		}
		
		System.out.println("Total tick count: " + tickCount);
		
		String ppmData = Canvas.canvasToPpm(canvas);
		Save.saveToDiskAsText(absoluteFilePath, ppmData);
	}
}
