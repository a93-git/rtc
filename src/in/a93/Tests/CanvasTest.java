package in.a93.Tests;

import in.a93.Canvas;
import in.a93.Color;
import in.a93.Save;

public class CanvasTest {
	
	public static void canvasTest() {
		Canvas c = new Canvas(10, 20);
		System.out.println("Canvas width is: " + c.getCanvasWidth());
		System.out.println("Canvas height is: " + c.getCanvasHeight());
		for (int i = 0; i < c.getCanvasWidth(); i++) {
			for (int j = 0; j < c.getCanvasHeight(); j++) {
				Color t = c.getColorAt(i, j);
				if (!(t.getRed() == 0 && t.getBlue() == 0 && t.getGreen() == 0)) System.out.println("Pixel at " + i + ", " + j + " is not black");
				else System.out.println("Pixel at " + i + ", " + j + " is black");
			}
		}
		
		Color red = new Color(1, 0, 0);
		Canvas.writePixelAt(c, 2, 3, red);
		
		System.out.println("Expected color at position (2, 3): " + red);
		System.out.println("Actual color at position (2, 3): " + Canvas.pixelAt(c, 2, 3));
		
		System.out.println("Expected output for canvasToPpm function: \nP3\n10 20\n255");
		System.out.println("Actual output for canvasToPpm function: \n" + Canvas.canvasToPpm(c));
		
		Canvas c2 = new Canvas(5, 3);
		Color col1 = new Color(1.5f, 0.0f, 0.0f);
		Color col2 = new Color(0.0f, 0.5f, 0.0f);
		Color col3 = new Color(-0.5f, 0.0f, 1.0f);
		
		Canvas.writePixelAt(c2, 0, 0, col1);
		Canvas.writePixelAt(c2, 2, 1, col2);
		Canvas.writePixelAt(c2, 4, 2, col3);

		for (int j = 0; j < c2.getCanvasHeight(); j++) {
			for (int i = 0; i < c2.getCanvasWidth(); i++) {
				Color t = c2.getColorAt(i, j);
				if (!(t.getRed() == 0 && t.getBlue() == 0 && t.getGreen() == 0)) System.out.println("Pixel at " + i + ", " + j + " is not black");
			}
		}
		
		System.out.println("Expected output for canvasToPpm function: \nP3\n10 20\n255\n255 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n0 0 0 0 0 0 0 128 0 0 0 0 0 0 0\n0 0 0 0 0 0 0 0 0 0 0 0 0 0 255\n");
		System.out.println("Actual output for canvasToPpm function: \n" + Canvas.canvasToPpm(c2));
		
		Canvas c3 = new Canvas(10, 2);
		Color col4 = new Color(1.0f, 0.8f, 0.6f);
		
		for (int i = 0; i < c3.getCanvasWidth(); i++) {
			for (int j = 0; j < c3.getCanvasHeight(); j++) {
				c3.setColorAt(i, j, col4);
			}
		}

		String _canvasColors = Canvas.canvasToPpm(c3);
		System.out.println("Actual output for canvasToPpm function: \n" + _canvasColors);
		
		System.out.println("The PPM string ends with a newline character: " + _canvasColors.endsWith("\n"));
		
		// Save to disk
		Save.saveToDiskAsText("E:\\rt.ppm", _canvasColors);
	}
}
