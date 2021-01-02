package in.a93.Tests;

import in.a93.Matrix;
import in.a93.Point;
import in.a93.Vector;
import in.a93.Exceptions.MatrixNotInvertibleException;

public class MatrixTransformationTest {
	public static void matrixTranslationTest() {
		Matrix translate = Matrix.translation(5, -3, 2);
		Point p = new Point(-3, 4, 5);
		
		Matrix p_matrix = Matrix.tuple2Matrix(p);
		
		System.out.println("Expected output of point and translation matrix multiplication: \n(2, 1, 7, 1)");
		System.out.println("Actual output of point and translation matrix multiplication: \n" + Matrix.matrix2Point(Matrix.multiply(translate, p_matrix)));
		
		Matrix translateInverse = null;
		try {
			translateInverse = Matrix.getInverseMatrix(translate);
		} catch (MatrixNotInvertibleException e) {
			e.printStackTrace();
		}
		System.out.println("Expected output of point and inverse translation matrix multiplication: \n (-8, 7, 3, 1)");
		System.out.println("Actual output of point and inverse translation matrix multiplication: \n" + Matrix.matrix2Point(Matrix.multiply(translateInverse, p_matrix)));
		
		Vector v = new Vector(-3, 4, 5);
		System.out.println("Actual vector value is: " + v);
		System.out.println("Moving a vector around in space doesn't change it: " + v.equals(Matrix.matrix2Vector(Matrix.multiply(translate, Matrix.tuple2Matrix(v)))));		
	}
	
	public static void matrixScalingTest() {
		Matrix scaling = Matrix.scaling(2, 3, 4);
		Point p2 = new Point(-4, 6, 8);
		
		System.out.println("Expected output of scaling a point: \n(-8, 18, 32, 1)");
		System.out.println("Actual output of scaling a point: \n" + Matrix.matrix2Point(Matrix.multiply(scaling, Matrix.tuple2Matrix(p2))));
		
		Vector v2 = new Vector(-4, 6, 8);
		
		System.out.println("Expected output of scaling a vector: \n(-8, 18, 32, 0)");
		System.out.println("Actual output of scaling a vector: \n" + Matrix.matrix2Vector(Matrix.multiply(scaling, Matrix.tuple2Matrix(v2))));
		
		Matrix scalingInverse = null;
		
		try {
			scalingInverse = Matrix.getInverseMatrix(scaling);
		} catch (MatrixNotInvertibleException e) {
			e.printStackTrace();
		}
		
		System.out.println("Expected output of DOWN scaling a vector: \n(-2, 2, 2, 0)");
		System.out.println("Actual output of DOWN scaling a vector: \n" + Matrix.matrix2Vector(Matrix.multiply(scalingInverse, Matrix.tuple2Matrix(v2))));
		
		Matrix reflection = Matrix.reflection(-1, 1, 1);
		Point p3 = new Point(2, 3, 4);
		
		System.out.println("Expected output of reflecting a point on x axis: \n(-2, 3, 4, 1)");
		System.out.println("Expected output of reflecting a point on x axis: \n" + Matrix.matrix2Point(Matrix.multiply(reflection, Matrix.tuple2Matrix(p3))));
		
	}
	
	public static void matrixRotationTest() {
		Point p4 = new Point(0, 1, 0);
		float halfQuarter = (float) Math.PI / 4;
		float fullQuarter = (float) Math.PI / 2;
		
		Matrix rotationX = Matrix.rotationX(halfQuarter);
		Matrix rotationX2 = Matrix.rotationX(fullQuarter);
		
		System.out.println("Expected output of rotating a point half quarter around x axis: \n(0, sqrt(2)/2, sqrt(2)/2)");
		System.out.println("Actual output of rotating a point half quarter around x axis: \n" + Matrix.matrix2Point(Matrix.multiply(rotationX, Matrix.tuple2Matrix(p4))));
		System.out.println("Expected output of rotating a point full quarter around x axis: \n(0, 0, 1");
		System.out.println("Actual output of rotating a point full quarter around x axis: \n" + Matrix.matrix2Point(Matrix.multiply(rotationX2, Matrix.tuple2Matrix(p4))));
		
		Matrix rotationXInverse = null;
		
		try {
			rotationXInverse = Matrix.getInverseMatrix(rotationX);
		} catch (MatrixNotInvertibleException e) {

			e.printStackTrace();
		}
		
		System.out.println("Expected output of rotating a point half quarter around x axis (anti-clockwise): \n(0, sqrt(2)/2, -sqrt(2)/2)");
		System.out.println("Actual output of rotating a point half quarter around x axis: \n" + Matrix.matrix2Point(Matrix.multiply(rotationXInverse, Matrix.tuple2Matrix(p4))));
		
		Point p5 = new Point(0, 0, 1);
		
		Matrix rotationY = Matrix.rotationY(halfQuarter);
		Matrix rotationY2 = Matrix.rotationY(fullQuarter);
		
		System.out.println("Expected output of rotating a point half quarter around y axis: \n(sqrt(2)/2, 0, sqrt(2)/2)");
		System.out.println("Actual output of rotating a point half quarter around y axis: \n" + Matrix.matrix2Point(Matrix.multiply(rotationY, Matrix.tuple2Matrix(p5))));
		System.out.println("Expected output of rotating a point full quarter around y axis: \n(1, 0, 0");
		System.out.println("Actual output of rotating a point full quarter around y axis: \n" + Matrix.matrix2Point(Matrix.multiply(rotationY2, Matrix.tuple2Matrix(p5))));

		Point p6 = new Point(0, 1, 0);
		
		Matrix rotationZ = Matrix.rotationZ(halfQuarter);
		Matrix rotationZ2 = Matrix.rotationZ(fullQuarter);
		
		System.out.println("Expected output of rotating a point half quarter around z axis: \n(-sqrt(2)/2, sqrt(2)/2), 0");
		System.out.println("Actual output of rotating a point half quarter around z axis: \n" + Matrix.matrix2Point(Matrix.multiply(rotationZ, Matrix.tuple2Matrix(p6))));
		System.out.println("Expected output of rotating a point full quarter around z axis: \n(-1, 0, 0");
		System.out.println("Actual output of rotating a point full quarter around z axis: \n" + Matrix.matrix2Point(Matrix.multiply(rotationZ2, Matrix.tuple2Matrix(p6))));
		
	}
	
	public static void matrixShearingTest() {
		Point p = new Point(2, 3, 4);
		
		Matrix shearing = Matrix.shearing(1, 0, 0, 0, 0, 0);
		System.out.println("Expected: (5, 3, 4, 1)\tActual: " + Matrix.matrix2Point(Matrix.multiply(shearing, Matrix.tuple2Matrix(p))));
		
		Matrix shearing2 = Matrix.shearing(0, 1, 0, 0, 0, 0);
		System.out.println("Expected: (6, 3, 4, 1)\tActual: " + Matrix.matrix2Point(Matrix.multiply(shearing2, Matrix.tuple2Matrix(p))));
		
		Matrix shearing3 = Matrix.shearing(0, 0, 1, 0, 0, 0);
		System.out.println("Expected: (2, 5, 4, 1)\tActual: " + Matrix.matrix2Point(Matrix.multiply(shearing3, Matrix.tuple2Matrix(p))));
		
		Matrix shearing4 = Matrix.shearing(0, 0, 0, 1, 0, 0);
		System.out.println("Expected: (2, 7, 4, 1)\tActual: " + Matrix.matrix2Point(Matrix.multiply(shearing4, Matrix.tuple2Matrix(p))));
		
		Matrix shearing5 = Matrix.shearing(0, 0, 0, 0, 1, 0);
		System.out.println("Expected: (2, 3, 6, 1)\tActual: " + Matrix.matrix2Point(Matrix.multiply(shearing5, Matrix.tuple2Matrix(p))));
		
		Matrix shearing6 = Matrix.shearing(0, 0, 0, 0, 0, 1);
		System.out.println("Expected: (2, 3, 7, 1)\tActual: " + Matrix.matrix2Point(Matrix.multiply(shearing6, Matrix.tuple2Matrix(p))));
	}
	
	public static void matrixTransformationChaining() {
		Matrix transform = Matrix.getIdentityMatrix(4, 4).rotateX((float) Math.PI / 2).scale(5, 5, 5).translate(10, 5, 7);
		Point p = new Point(1, 0, 1);
		System.out.println("Expected: (15, 0, 7, 1)\tActual: " + Matrix.matrix2Point(Matrix.multiply(transform, Matrix.tuple2Matrix(p))));
	}
}
