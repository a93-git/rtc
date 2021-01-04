package in.a93.Tests;

import in.a93.Matrix;
import in.a93.Exceptions.MatrixNotInvertibleException;

public class MatrixTest {
	
	public static void matrixTest() {
		Matrix m4x4 = new Matrix(4, 4);
		m4x4.setElementAt(0, 0, 1);
		m4x4.setElementAt(0, 1, 2);
		m4x4.setElementAt(0, 2, 3);
		m4x4.setElementAt(0, 3, 4);
		m4x4.setElementAt(1, 0, 5.5f);
		m4x4.setElementAt(1, 1, 6.5f);
		m4x4.setElementAt(1, 2, 7.5f);
		m4x4.setElementAt(1, 3, 8.5f);
		m4x4.setElementAt(2, 0, 9);
		m4x4.setElementAt(2, 1, 10);
		m4x4.setElementAt(2, 2, 11);
		m4x4.setElementAt(2, 3, 12);
		m4x4.setElementAt(3, 0, 13.5f);
		m4x4.setElementAt(3, 1, 14.5f);
		m4x4.setElementAt(3, 2, 15.5f);
		m4x4.setElementAt(3, 3, 16.5f);

		System.out.println("Element at 0, 0 is: " + m4x4.getElementAt(0, 0));
		System.out.println("Element at 0, 3 is: " + m4x4.getElementAt(0, 3));
		System.out.println("Element at 1, 0 is: " + m4x4.getElementAt(1, 0));
		System.out.println("Element at 1, 2 is: " + m4x4.getElementAt(1, 2));
		System.out.println("Element at 2, 2 is: " + m4x4.getElementAt(2, 2));
		System.out.println("Element at 3, 0 is: " + m4x4.getElementAt(3, 0));
		System.out.println("Element at 3, 2 is: " + m4x4.getElementAt(3, 2));
		
		Matrix m2x2 = new Matrix(2, 2);
		m2x2.setElementAt(0, 0, -3);
		m2x2.setElementAt(0, 1, 5);
		m2x2.setElementAt(1, 0, 1);
		m2x2.setElementAt(1, 1, -2);
		
		System.out.println("Element at 0, 0 is: " + m2x2.getElementAt(0, 0));
		System.out.println("Element at 0, 1 is: " + m2x2.getElementAt(0, 1));
		System.out.println("Element at 1, 0 is: " + m2x2.getElementAt(1, 0));
		System.out.println("Element at 1, 1 is: " + m2x2.getElementAt(1, 1));
		
		Matrix m3x3 = new Matrix(3, 3);
		m3x3.setElementAt(0, 0, -3);
		m3x3.setElementAt(0, 1, 5);
		m3x3.setElementAt(0, 2, 0);
		m3x3.setElementAt(1, 0, 1);
		m3x3.setElementAt(1, 1, -2);
		m3x3.setElementAt(1, 2, -7);
		m3x3.setElementAt(2, 0, 0);
		m3x3.setElementAt(2, 1, 1);
		m3x3.setElementAt(2, 2, 1);
		
		System.out.println("Element at 0, 0 is: " + m3x3.getElementAt(0, 0));
		System.out.println("Element at 1, 1 is: " + m3x3.getElementAt(1, 1));
		System.out.println("Element at 2, 2 is: " + m3x3.getElementAt(2, 2));
		
		Matrix m4x4_2 = new Matrix(4, 4);
		m4x4_2.setElementAt(0, 0, 1);
		m4x4_2.setElementAt(0, 1, 2);
		m4x4_2.setElementAt(0, 2, 3);
		m4x4_2.setElementAt(0, 3, 4);
		m4x4_2.setElementAt(1, 0, 5.5f);
		m4x4_2.setElementAt(1, 1, 6.5f);
		m4x4_2.setElementAt(1, 2, 7.5f);
		m4x4_2.setElementAt(1, 3, 8.5f);
		m4x4_2.setElementAt(2, 0, 9);
		m4x4_2.setElementAt(2, 1, 10);
		m4x4_2.setElementAt(2, 2, 11);
		m4x4_2.setElementAt(2, 3, 12);
		m4x4_2.setElementAt(3, 0, 13.5f);
		m4x4_2.setElementAt(3, 1, 14.5f);
		m4x4_2.setElementAt(3, 2, 15.5f);
		m4x4_2.setElementAt(3, 3, 16.5f);
		
		System.out.println("m4x4 and m4x4_2 are equal: " + m4x4.equals(m4x4_2));
		System.out.println("m4x4 and m3x3 are equal: " + m4x4.equals(m3x3));
		
		Matrix m4x4_3 = new Matrix(4, 4);
		m4x4_3.setElementAt(0, 0, 1);
		m4x4_3.setElementAt(0, 1, 2);
		m4x4_3.setElementAt(0, 2, 3);
		m4x4_3.setElementAt(0, 3, 4);
		m4x4_3.setElementAt(1, 0, 5);
		m4x4_3.setElementAt(1, 1, 6);
		m4x4_3.setElementAt(1, 2, 7);
		m4x4_3.setElementAt(1, 3, 8);
		m4x4_3.setElementAt(2, 0, 9);
		m4x4_3.setElementAt(2, 1, 8);
		m4x4_3.setElementAt(2, 2, 7);
		m4x4_3.setElementAt(2, 3, 6);
		m4x4_3.setElementAt(3, 0, 5);
		m4x4_3.setElementAt(3, 1, 4);
		m4x4_3.setElementAt(3, 2, 3);
		m4x4_3.setElementAt(3, 3, 2);
		
		Matrix m4x4_4 = new Matrix(4, 4);
		m4x4_4.setElementAt(0, 0, -2);
		m4x4_4.setElementAt(0, 1, 1);
		m4x4_4.setElementAt(0, 2, 2);
		m4x4_4.setElementAt(0, 3, 3);
		m4x4_4.setElementAt(1, 0, 3);
		m4x4_4.setElementAt(1, 1, 2);
		m4x4_4.setElementAt(1, 2, 1);
		m4x4_4.setElementAt(1, 3, -1);
		m4x4_4.setElementAt(2, 0, 4);
		m4x4_4.setElementAt(2, 1, 3);
		m4x4_4.setElementAt(2, 2, 6);
		m4x4_4.setElementAt(2, 3, 5);
		m4x4_4.setElementAt(3, 0, 1);
		m4x4_4.setElementAt(3, 1, 2);
		m4x4_4.setElementAt(3, 2, 7);
		m4x4_4.setElementAt(3, 3, 8);
		
		System.out.println("Result of m4x4_3 and m4x4_4 is: \n" + Matrix.multiply(m4x4_3, m4x4_4));
		
		Matrix m4x4_5 = new Matrix(4, 4);
		m4x4_5.setElementAt(0, 0, 1);
		m4x4_5.setElementAt(0, 1, 2);
		m4x4_5.setElementAt(0, 2, 3);
		m4x4_5.setElementAt(0, 3, 4);
		m4x4_5.setElementAt(1, 0, 2);
		m4x4_5.setElementAt(1, 1, 4);
		m4x4_5.setElementAt(1, 2, 4);
		m4x4_5.setElementAt(1, 3, 2);
		m4x4_5.setElementAt(2, 0, 8);
		m4x4_5.setElementAt(2, 1, 6);
		m4x4_5.setElementAt(2, 2, 4);
		m4x4_5.setElementAt(2, 3, 1);
		m4x4_5.setElementAt(3, 0, 0);
		m4x4_5.setElementAt(3, 1, 0);
		m4x4_5.setElementAt(3, 2, 0);
		m4x4_5.setElementAt(3, 3, 1);
		
		Matrix m4x1 = new Matrix(4, 1);
		m4x1.setElementAt(0, 0, 1);
		m4x1.setElementAt(1, 0, 2);
		m4x1.setElementAt(2, 0, 3);
		m4x1.setElementAt(3, 0, 1);
		
		System.out.println("Result of m4x4_5 and m4x1 is: \n" + Matrix.multiply(m4x4_5, m4x1));
		
		Matrix m4x4_6 = new Matrix(4, 4);
		m4x4_6.setElementAt(0, 0, 0);
		m4x4_6.setElementAt(0, 1, 1);
		m4x4_6.setElementAt(0, 2, 2);
		m4x4_6.setElementAt(0, 3, 4);
		m4x4_6.setElementAt(1, 0, 1);
		m4x4_6.setElementAt(1, 1, 2);
		m4x4_6.setElementAt(1, 2, 4);
		m4x4_6.setElementAt(1, 3, 8);
		m4x4_6.setElementAt(2, 0, 2);
		m4x4_6.setElementAt(2, 1, 4);
		m4x4_6.setElementAt(2, 2, 8);
		m4x4_6.setElementAt(2, 3, 16);
		m4x4_6.setElementAt(3, 0, 4);
		m4x4_6.setElementAt(3, 1, 8);
		m4x4_6.setElementAt(3, 2, 16);
		m4x4_6.setElementAt(3, 3, 32);
		
		Matrix identity_matrix = new Matrix(4, 4);
		identity_matrix.setElementAt(0, 0, 1);
		identity_matrix.setElementAt(0, 1, 0);
		identity_matrix.setElementAt(0, 2, 0);
		identity_matrix.setElementAt(0, 3, 0);
		identity_matrix.setElementAt(1, 0, 0);
		identity_matrix.setElementAt(1, 1, 1);
		identity_matrix.setElementAt(1, 2, 0);
		identity_matrix.setElementAt(1, 3, 0);
		identity_matrix.setElementAt(2, 0, 0);
		identity_matrix.setElementAt(2, 1, 0);
		identity_matrix.setElementAt(2, 2, 1);
		identity_matrix.setElementAt(2, 3, 0);
		identity_matrix.setElementAt(3, 0, 0);
		identity_matrix.setElementAt(3, 1, 0);
		identity_matrix.setElementAt(3, 2, 0);
		identity_matrix.setElementAt(3, 3, 1);
		
		System.out.println("Result of multiplying a m4x4_6 with an identity matrix is: \n" + Matrix.multiply(m4x4_6, identity_matrix));
		
		System.out.println("Transpose of an identity matrix is an identity matrix: \n" + Matrix.transpose(identity_matrix));
				
		System.out.println("m4x4_6 matrix is: \n" + m4x4_6);
		System.out.println("Transpose of m4x4_6 is: \n" + Matrix.transpose(m4x4_6));
		
		Matrix m2x2_2 = new Matrix(2, 2);
		m2x2_2.setElementAt(0, 0, 1);
		m2x2_2.setElementAt(0, 1, 5);
		m2x2_2.setElementAt(1, 0, -3);
		m2x2_2.setElementAt(1, 1, 2);
		
		System.out.println("Determinant of m2x2_2 matrix is: \n" + Matrix.getDeterminant(m2x2_2));
		
		System.out.println("m4x4_6 matrix is: \n" + m4x4_6);
		System.out.println("Submatrix of m4x4_6 matrix after removing 2nd row and 3rd column is: \n" + Matrix.getSubmatrix(m4x4_6, 1, 2));
		
		Matrix m3x3_2 = new Matrix(3, 3);
		m3x3_2.setElementAt(0, 0, 1);
		m3x3_2.setElementAt(0, 1, 5);
		m3x3_2.setElementAt(0, 2, 0);
		m3x3_2.setElementAt(1, 0, -3);
		m3x3_2.setElementAt(1, 1, 2);
		m3x3_2.setElementAt(1, 2, 7);
		m3x3_2.setElementAt(2, 0, 0);
		m3x3_2.setElementAt(2, 1, 6);
		m3x3_2.setElementAt(2, 2, -3);
		
		System.out.println("m3x3_2 matrix is: \n" + m3x3_2);
		System.out.println("Submatrix of m3x3_2 matrix after removing 1st row and 3rd column is: \n" + Matrix.getSubmatrix(m3x3_2, 0, 2));
		
		Matrix m4x4_7 = new Matrix(4, 4);
		m4x4_7.setElementAt(0, 0, -6);
		m4x4_7.setElementAt(0, 1, 1);
		m4x4_7.setElementAt(0, 2, 1);
		m4x4_7.setElementAt(0, 3, 6);
		m4x4_7.setElementAt(1, 0, -8);
		m4x4_7.setElementAt(1, 1, 5);
		m4x4_7.setElementAt(1, 2, 8);
		m4x4_7.setElementAt(1, 3, 6);
		m4x4_7.setElementAt(2, 0, -1);
		m4x4_7.setElementAt(2, 1, 0);
		m4x4_7.setElementAt(2, 2, 8);
		m4x4_7.setElementAt(2, 3, 2);
		m4x4_7.setElementAt(3, 0, -7);
		m4x4_7.setElementAt(3, 1, 1);
		m4x4_7.setElementAt(3, 2, -1);
		m4x4_7.setElementAt(3, 3, 1);
		
		System.out.println("m4x4_7 matrix is: \n" + m4x4_7);
		System.out.println("Submatrix of m4x4_7 matrix after removing 3rd row and 2nd column is: \n" + Matrix.getSubmatrix(m4x4_7, 2, 1));
		
		Matrix m3x3_3 = new Matrix(3, 3);
		m3x3_3.setElementAt(0, 0, 3);
		m3x3_3.setElementAt(0, 1, 5);
		m3x3_3.setElementAt(0, 2, 0);
		m3x3_3.setElementAt(1, 0, 2);
		m3x3_3.setElementAt(1, 1, -1);
		m3x3_3.setElementAt(1, 2, -7);
		m3x3_3.setElementAt(2, 0, 6);
		m3x3_3.setElementAt(2, 1, -1);
		m3x3_3.setElementAt(2, 2, 5);
		
		System.out.println("m3x3_3 matrix is: \n" + m3x3_3);
		System.out.println("Minor of m3x3_3 matrix at 0, 0 is: \n" + Matrix.getMinor(m3x3_3, 0, 0));
		System.out.println("Cofactor of m3x3_3 matrix at 0, 0 is: \n" + Matrix.getCofactor(m3x3_3, 0, 0));
		System.out.println("Minor of m3x3_3 matrix at 1, 0 is: \n" + Matrix.getMinor(m3x3_3, 1, 0));
		System.out.println("Cofactor of m3x3_3 matrix at 1, 0 is: \n" + Matrix.getCofactor(m3x3_3, 1, 0));
		
		Matrix m3x3_4 = new Matrix(3, 3);
		m3x3_4.setElementAt(0, 0, 1);
		m3x3_4.setElementAt(0, 1, 2);
		m3x3_4.setElementAt(0, 2, 6);
		m3x3_4.setElementAt(1, 0, -5);
		m3x3_4.setElementAt(1, 1, 8);
		m3x3_4.setElementAt(1, 2, -4);
		m3x3_4.setElementAt(2, 0, 2);
		m3x3_4.setElementAt(2, 1, 6);
		m3x3_4.setElementAt(2, 2, 4);
		
		System.out.println("Cofactor of m3x3_4 matrix at 0, 0 is: \n" + Matrix.getCofactor(m3x3_4, 0, 0));
		System.out.println("Cofactor of m3x3_4 matrix at 0, 1 is: \n" + Matrix.getCofactor(m3x3_4, 0, 1));
		System.out.println("Cofactor of m3x3_4 matrix at 0, 2 is: \n" + Matrix.getCofactor(m3x3_4, 0, 2));
		System.out.println("Determinant of m3x3_4 matrix is: \n" + Matrix.getDeterminant(m3x3_4));		
		
		Matrix m4x4_8 = new Matrix(4, 4);
		m4x4_8.setElementAt(0, 0, -2);
		m4x4_8.setElementAt(0, 1, -8);
		m4x4_8.setElementAt(0, 2, 3);
		m4x4_8.setElementAt(0, 3, 5);
		m4x4_8.setElementAt(1, 0, -3);
		m4x4_8.setElementAt(1, 1, 1);
		m4x4_8.setElementAt(1, 2, 7);
		m4x4_8.setElementAt(1, 3, 3);
		m4x4_8.setElementAt(2, 0, 1);
		m4x4_8.setElementAt(2, 1, 2);
		m4x4_8.setElementAt(2, 2, -9);
		m4x4_8.setElementAt(2, 3, 6);
		m4x4_8.setElementAt(3, 0, -6);
		m4x4_8.setElementAt(3, 1, 7);
		m4x4_8.setElementAt(3, 2, 7);
		m4x4_8.setElementAt(3, 3, -9);
		
		System.out.println("m4x4_8 matrix is: \n" + m4x4_8);
		System.out.println("Cofactor of m4x4_8 matrix at 0, 0 is: \n" + Matrix.getCofactor(m4x4_8, 0, 0));
		System.out.println("Cofactor of m4x4_8 matrix at 0, 1 is: \n" + Matrix.getCofactor(m4x4_8, 0, 1));
		System.out.println("Cofactor of m4x4_8 matrix at 0, 2 is: \n" + Matrix.getCofactor(m4x4_8, 0, 2));
		System.out.println("Cofactor of m4x4_8 matrix at 0, 3 is: \n" + Matrix.getCofactor(m4x4_8, 0, 3));
		System.out.println("Determinant of m4x4_8 matrix is: \n" + Matrix.getDeterminant(m4x4_8));		
		
		Matrix m4x4_9 = new Matrix(4, 4);
		m4x4_9.setElementAt(0, 0, -4);
		m4x4_9.setElementAt(0, 1, 2);
		m4x4_9.setElementAt(0, 2, -2);
		m4x4_9.setElementAt(0, 3, -3);
		m4x4_9.setElementAt(1, 0, 9);
		m4x4_9.setElementAt(1, 1, 6);
		m4x4_9.setElementAt(1, 2, 2);
		m4x4_9.setElementAt(1, 3, 6);
		m4x4_9.setElementAt(2, 0, 0);
		m4x4_9.setElementAt(2, 1, -5);
		m4x4_9.setElementAt(2, 2, 1);
		m4x4_9.setElementAt(2, 3, -5);
		m4x4_9.setElementAt(3, 0, 0);
		m4x4_9.setElementAt(3, 1, 0);
		m4x4_9.setElementAt(3, 2, 0);
		m4x4_9.setElementAt(3, 3, 0);
		
		System.out.println("m4x4_9 matrix is: \n" + m4x4_9);
		System.out.println("Determinant of m4x4_9 matrix is: \n" + Matrix.getDeterminant(m4x4_9));
		System.out.println("m4x4_9 matrix is invertible: " + Matrix.isInvertible(m4x4_9));
		
		Matrix m4x4_10 = new Matrix(4, 4);
		m4x4_10.setElementAt(0, 0, -5);
		m4x4_10.setElementAt(0, 1, 2);
		m4x4_10.setElementAt(0, 2, 6);
		m4x4_10.setElementAt(0, 3, -8);
		m4x4_10.setElementAt(1, 0, 1);
		m4x4_10.setElementAt(1, 1, -5);
		m4x4_10.setElementAt(1, 2, 1);
		m4x4_10.setElementAt(1, 3, 8);
		m4x4_10.setElementAt(2, 0, 7);
		m4x4_10.setElementAt(2, 1, 7);
		m4x4_10.setElementAt(2, 2, -6);
		m4x4_10.setElementAt(2, 3, -7);
		m4x4_10.setElementAt(3, 0, 1);
		m4x4_10.setElementAt(3, 1, -3);
		m4x4_10.setElementAt(3, 2, 7);
		m4x4_10.setElementAt(3, 3, 4);
		
		try {
			System.out.println("Inverse of m4x4_10 matrix is: \n" + Matrix.getInverseMatrix(m4x4_10));
		} catch (MatrixNotInvertibleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Matrix m4x4_11 = new Matrix(4, 4);
		m4x4_11.setElementAt(0, 0, 8);
		m4x4_11.setElementAt(0, 1, -5);
		m4x4_11.setElementAt(0, 2, 9);
		m4x4_11.setElementAt(0, 3, 2);
		m4x4_11.setElementAt(1, 0, 7);
		m4x4_11.setElementAt(1, 1, 5);
		m4x4_11.setElementAt(1, 2, 6);
		m4x4_11.setElementAt(1, 3, 1);
		m4x4_11.setElementAt(2, 0, -6);
		m4x4_11.setElementAt(2, 1, 0);
		m4x4_11.setElementAt(2, 2, 9);
		m4x4_11.setElementAt(2, 3, 6);
		m4x4_11.setElementAt(3, 0, -3);
		m4x4_11.setElementAt(3, 1, 0);
		m4x4_11.setElementAt(3, 2, -9);
		m4x4_11.setElementAt(3, 3, -4);
		
		System.out.println("Determinant of m4x4_11 matrix is: \n" + Matrix.getDeterminant(m4x4_11));
		
		try {
			System.out.println("Inverse of m4x4_11 matrix is: \n" + Matrix.getInverseMatrix(m4x4_11));
		} catch (MatrixNotInvertibleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Matrix m4x4_12 = new Matrix(4, 4);
		m4x4_12.setElementAt(0, 0, 9);
		m4x4_12.setElementAt(0, 1, 3);
		m4x4_12.setElementAt(0, 2, 0);
		m4x4_12.setElementAt(0, 3, 9);
		m4x4_12.setElementAt(1, 0, -5);
		m4x4_12.setElementAt(1, 1, -2);
		m4x4_12.setElementAt(1, 2, -6);
		m4x4_12.setElementAt(1, 3, -3);
		m4x4_12.setElementAt(2, 0, -4);
		m4x4_12.setElementAt(2, 1, 9);
		m4x4_12.setElementAt(2, 2, 6);
		m4x4_12.setElementAt(2, 3, 4);
		m4x4_12.setElementAt(3, 0, -7);
		m4x4_12.setElementAt(3, 1, 6);
		m4x4_12.setElementAt(3, 2, 6);
		m4x4_12.setElementAt(3, 3, 2);
		
		System.out.println("Determinant of m4x4_12 matrix is: \n" + Matrix.getDeterminant(m4x4_12));
		
		try {
			System.out.println("Inverse of m4x4_12 matrix is: \n" + Matrix.getInverseMatrix(m4x4_12));
		} catch (MatrixNotInvertibleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Matrix m4x4_13 = new Matrix(4, 4);
		m4x4_13.setElementAt(0, 0, 3);
		m4x4_13.setElementAt(0, 1, -9);
		m4x4_13.setElementAt(0, 2, 7);
		m4x4_13.setElementAt(0, 3, 3);
		m4x4_13.setElementAt(1, 0, 3);
		m4x4_13.setElementAt(1, 1, -8);
		m4x4_13.setElementAt(1, 2, 2);
		m4x4_13.setElementAt(1, 3, -9);
		m4x4_13.setElementAt(2, 0, -4);
		m4x4_13.setElementAt(2, 1, 4);
		m4x4_13.setElementAt(2, 2, 4);
		m4x4_13.setElementAt(2, 3, 1);
		m4x4_13.setElementAt(3, 0, -6);
		m4x4_13.setElementAt(3, 1, 5);
		m4x4_13.setElementAt(3, 2, -1);
		m4x4_13.setElementAt(3, 3, 1);
		
		Matrix m4x4_14 = new Matrix(4, 4);
		m4x4_14.setElementAt(0, 0, 8);
		m4x4_14.setElementAt(0, 1, 2);
		m4x4_14.setElementAt(0, 2, 2);
		m4x4_14.setElementAt(0, 3, 2);
		m4x4_14.setElementAt(1, 0, 3);
		m4x4_14.setElementAt(1, 1, -1);
		m4x4_14.setElementAt(1, 2, 7);
		m4x4_14.setElementAt(1, 3, 0);
		m4x4_14.setElementAt(2, 0, 7);
		m4x4_14.setElementAt(2, 1, 0);
		m4x4_14.setElementAt(2, 2, 5);
		m4x4_14.setElementAt(2, 3, 4);
		m4x4_14.setElementAt(3, 0, 6);
		m4x4_14.setElementAt(3, 1, -2);
		m4x4_14.setElementAt(3, 2, 0);
		m4x4_14.setElementAt(3, 3, 5);
		
		Matrix m4x4_15 = Matrix.multiply(m4x4_13, m4x4_14);
		
		System.out.println("Product of matrix m4x4_13 and m4x4_14 is: \n" + m4x4_15);
		try {
			System.out.println("Product of matrix m4x4_15 and inverse of m4x4_14 is equal to m4x4_13: \n" + m4x4_13.equals(Matrix.multiply(m4x4_15, Matrix.getInverseMatrix(m4x4_14))));
		} catch (MatrixNotInvertibleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			System.out.println("Inverse of identity matrix is: \n" + Matrix.getInverseMatrix(identity_matrix));
		} catch (MatrixNotInvertibleException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("Product of a matrix and its inverse is: \n" + Matrix.multiply(m4x4_14, Matrix.getInverseMatrix(m4x4_14)));
		} catch (MatrixNotInvertibleException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

	public static void test2() {
		Matrix a = Matrix.getIdentityMatrix(4, 4);
		Matrix b = null;
		try {
			b = Matrix.getInverseMatrix(a);
		} catch (MatrixNotInvertibleException e) {
			e.printStackTrace();
		}
		
		System.out.println(a);
		System.out.println(b);
	}

}
