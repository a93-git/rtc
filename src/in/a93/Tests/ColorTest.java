package in.a93.Tests;

import in.a93.Color;

public class ColorTest {
	Color c = new Color(-0.5f, 0.4f, 1.7f);
	
	public void getColors() {
		System.out.println("c.red: " + c.getRed());
		System.out.println("c.blue: " + c.getBlue());
		System.out.println("c.green: " + c.getGreen());
	}
	
	public void addColors() {
		Color c1 = new Color(0.9f, 0.6f, 0.75f);
		Color c2 = new Color(0.7f, 0.1f, 0.25f);
		
		System.out.println("Expected output of color addition: (1.6, 0.7, 1.0)");
		System.out.println("Actual output of color addition: " + (c1.add(c2)));
	}

	public void subtractColors() {
		Color c1 = new Color(0.9f, 0.6f, 0.75f);
		Color c2 = new Color(0.7f, 0.1f, 0.25f);
		
		System.out.println("Expected output of color subtraction: (0.2, 0.5, 0.5)");
		System.out.println("Actual output of color subtraction: " + (c1.subtract(c2)));
	}
	
	public void scalarMultiplicationColors() {
		Color c1 = new Color(0.2f, 0.3f, 0.4f);
		
		System.out.println("Expected output of color scalar multiplication: (0.4, 0.6, 0.8)");
		System.out.println("Actual output of color subtraction: " + (c1.scalarMultiply(2)));
	}
	
	public void schurProduct() {
		Color c1 = new Color(1.0f, 0.2f, 0.4f);
		Color c2 = new Color(0.9f, 1.0f, 0.1f);
		
		System.out.println("Expected output of color schur multiplication: (0.9, 0.2, 0.04)");
		System.out.println("Actual output of color subtraction: " + (Color.schurProduct(c1, c2)));
	}
	
	public static void colorTest() {
		ColorTest c = new ColorTest();
		c.getColors();
		c.addColors();
		c.subtractColors();
		c.scalarMultiplicationColors();
		c.schurProduct();
	}
}
