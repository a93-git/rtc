package in.a93.Tests;

import in.a93.Matrix;
import in.a93.Shape;
import in.a93.Material;

public class ShapesTest {
	public static void test1() {
		Shape testShape = new Shape();
		
		System.out.println(testShape.getTransform());
	}

	public static void test2() {
		Shape testShape = new Shape();
		testShape.setTransform(testShape.getTransform().translate(2, 3, 4));
		System.out.println(testShape.getTransform());
	}
	
	public static void test3() {
		Shape testShape = new Shape();
		Material m = testShape.getMaterial();
		
		System.out.println(m);
		
		Material m2 = new Material();
		
		m2.setAmbient(2000);
		
		testShape.setMaterial(m2);
		System.out.println(testShape.getMaterial());
		
	}
}
