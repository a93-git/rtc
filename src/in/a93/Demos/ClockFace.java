package in.a93.Demos;

import in.a93.Canvas;
import in.a93.Color;
import in.a93.Matrix;
import in.a93.Point;
import in.a93.Save;

public class ClockFace {
	private Canvas canvas = new Canvas(800, 800);
	private Point point = new Point(400, 400, 0);
	private Color color = new Color(1.0f, 1.0f, 1.0f);
	private float radius = (3.0f / 8.0f);
	public static final float DELTA = 0.0001f;
	
	public ClockFace() {}
	
	public void drawClockFace(String absoluteFilePath) {
		float angle = (float) Math.PI;
		int hour = 12;
		int xCoord = 0;
		int yCoord = 0;
		
		while (hour > 0) {
			
			angle = (float)(hour * Math.PI / 6);
			
			Matrix transform = Matrix.getIdentityMatrix(4, 4).rotateZ(angle);
			Point _tempPoint = Matrix.matrix2Point(Matrix.multiply(transform, Matrix.tuple2Matrix(point)));

			point.setX(_tempPoint.getX());
			point.setY(_tempPoint.getY());
//			point.setZ(_tempPoint.getZ());
			
			xCoord = (int) (point.getX() * radius) + 400;
			yCoord = (int) (point.getY() * radius) + 400;
			
			System.out.println("Hour: " + hour + " Co-ordinates: " + xCoord + ", " + yCoord + "\t Angle: " + angle);
			Canvas.colorSquare(canvas, xCoord, yCoord, color);

			hour--;
		}
		
		String ppmData = Canvas.canvasToPpm(canvas);
		Save.saveToDiskAsText(absoluteFilePath, ppmData);
	}
}
