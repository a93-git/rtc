package in.a93.Tests;

import in.a93.*;

public class TransformationTest {
	public static void test1() {
		Point from = new Point(0, 0, 0);
		Point to = new Point(0, 0, -1);
		Vector up = new Vector(0, 1, 0);
		
		Matrix t = Matrix.getViewTransform(from, to, up);
		System.out.println(t);
	}

	public static void test2() {
		Point from = new Point(0, 0, 0);
		Point to = new Point(0, 0, 1);
		Vector up = new Vector(0, 1, 0);
		
		Matrix t = Matrix.getViewTransform(from, to, up);
		System.out.println(t);
		System.out.println(Matrix.scaling(-1, 1, -1));
	}

	public static void test3() {
		Point from = new Point(0, 0, 8);
		Point to = new Point(0, 0, 0);
		Vector up = new Vector(0, 1, 0);
		
		Matrix t = Matrix.getViewTransform(from, to, up);
		System.out.println(t);
		System.out.println(Matrix.translation(0, 0, -8));
	}

	public static void test4() {
		Point from = new Point(1, 3, 2);
		Point to = new Point(4, -2, 8);
		Vector up = new Vector(1, 1, 0);
		
		Matrix t = Matrix.getViewTransform(from, to, up);
		System.out.println(t);
	}
}
