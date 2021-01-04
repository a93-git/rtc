package in.a93;

import in.a93.Exceptions.MatrixNotInvertibleException;

public class Matrix {
	/*
	 * Matrix's individual elements are addressed using (row, column) pair
	 */
	private int matrixRows;
	private int matrixColumns;
	private float[][] matrix;
	private static final float DELTA = 0.00001f;

	public Matrix() {
		this.matrixRows = 0;
		this.matrixColumns = 0;
		this.matrix = new float[this.matrixRows][this.matrixColumns];
	}
	
	public Matrix(int rows, int columns) {
		this.matrixRows = rows;
		this.matrixColumns = columns;
		this.matrix = new float[matrixRows][matrixColumns];

	}
	
	public float[][] getArray() { return this.matrix;	}
	
	public float getElementAt(int row, int col) { return this.matrix[row][col];	}
	
	public void setElementAt(int row, int col, float value) { this.matrix[row][col] = value; }
	
	@Override
	public String toString() {
		StringBuilder retval = new StringBuilder("[");
		for (int i = 0; i < this.matrixRows; i++) {
			for (int j = 0; j < this.matrixColumns; j++) {
				retval.append(matrix[i][j] + ", ");
			}
			retval.append("\n");
		}
		retval.append("]");
		
		return retval.toString();
	}
	
	public int getRowCount() { return this.matrixRows; }
	
	public int getColumnCount() { return this.matrixColumns; }
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		
		Matrix other = (Matrix) obj;
		if ((this.getRowCount() == other.getRowCount()) && (this.getColumnCount() == other.getColumnCount())) {
			boolean equality = true;
			
			for (int i = 0; i < this.getRowCount(); i++) {
				for (int j = 0; j < this.getColumnCount(); j++) {
					if (!((this.getElementAt(i, j) - other.getElementAt(i, j)) < Matrix.DELTA)) {
						equality = false;
					}
				}
			}
			return equality;
		} else return false;
	}

	public static Matrix getIdentityMatrix(int row, int column) {
		Matrix result = new Matrix(row, column);
		
		for (int i = 0; i < row; i++) {
			result.setElementAt(i, i, 1);
		}
		
		return result;
	}	
	
	public static Matrix scalarMultiplication(Matrix m, float scalarValue) {
		Matrix result = new Matrix(m.getRowCount(), m.getColumnCount());
		
		for (int i = 0; i < m.getRowCount(); i++) {
			for (int j = 0; j < m.getColumnCount(); j++) {
				m.setElementAt(i, j, m.getElementAt(i, j) * scalarValue);
			}
		}
		
		return result;
	}

	public static Matrix multiply(Matrix a, Matrix b) {
//		Matrix a = (Matrix) matrix1;
//		Matrix b = (Matrix) matrix2;
		
		Matrix result = new Matrix(a.getRowCount(), b.getColumnCount());
		
		int rowCount = a.getRowCount();
		int columnCount = b.getColumnCount();
		int _rowsInSecondMatrix = b.getRowCount();
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				float _tempResult = 0.0f;
				for (int k = 0; k < _rowsInSecondMatrix; k++) {
					_tempResult += (a.getElementAt(i, k) * b.getElementAt(k, j));
				}
				result.setElementAt(i, j, _tempResult);
			}
		}
		return result;
	}
	
	public static Matrix transpose(Matrix a) {
		int rowCount = a.getRowCount();
		int columnCount = a.getRowCount();
		
		Matrix result = new Matrix(columnCount, rowCount);
		
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				result.setElementAt(i, j, a.getElementAt(j, i));
			}
		}
		
		return result;
	}
	
	public static float getDeterminant(Matrix a) {
		float determinant = 0.0f;
		
		if (a.getColumnCount() == 2 && a.getRowCount() == 2) {
			return (a.getElementAt(0, 0) * a.getElementAt(1, 1)) - (a.getElementAt(0, 1) * a.getElementAt(1, 0));
		} else {
			for (int i = 0; i < a.getColumnCount(); i++) {
				determinant += (a.getElementAt(0, i) * Matrix.getCofactor(a, 0, i)); 
			}
		}
		return determinant;
	}
	
	public static Matrix getSubmatrix(Matrix a, int row, int column) {
		Matrix result = new Matrix(a.getRowCount() - 1, a.getColumnCount() - 1);
		
		for (int i = 0, m = 0; i < a.getRowCount(); i++) {
			if (i == row) continue;
			for (int j = 0, n = 0; j < a.getColumnCount(); j++) {
				if (j==column) continue;
//				System.out.println("i: " + i + ", j: " + j);
				result.setElementAt(m, n, a.getElementAt(i, j));
				n++; // only increment when a value is set
			}
			m++; // prevents false increment due to continue statement
		}
		return result;
	}
	
	public static float getMinor(Matrix a, int row, int column)  {
		Matrix submatrix = Matrix.getSubmatrix(a, row, column);
		float determinant = Matrix.getDeterminant(submatrix);
		
		return determinant;
	}
	
	public static float getCofactor(Matrix a, int row, int column) {
		float minor = Matrix.getMinor(a, row, column);
		
		return ((row + column) % 2 == 0) ? minor : (-1 * minor); 
	}
	
	public static boolean isInvertible(Matrix a) {
		if (Math.abs((Matrix.getDeterminant(a) - 0.0f)) < Matrix.DELTA) return false;
		else return true;
	}
	
	public static Matrix getInverseMatrix(Matrix a) throws MatrixNotInvertibleException {
		if (!(Matrix.isInvertible(a))) {
			throw new MatrixNotInvertibleException("Determinant of matrix is 0");
		}
		
		Matrix inverse = new Matrix(a.getRowCount(), a.getColumnCount());
		float determinant = Matrix.getDeterminant(a);
		
		for (int i = 0; i < a.getRowCount(); i++) {
			for (int j = 0; j < a.getColumnCount(); j++) {
				float cofactor = Matrix.getCofactor(a, i, j);
				inverse.setElementAt(j, i, cofactor / determinant);
			}
		}
		
		return inverse;
	}

	public static Matrix translation(float x, float y, float z) {
		Matrix result = Matrix.getIdentityMatrix(4, 4);
		
		result.setElementAt(0, 3, x);
		result.setElementAt(1, 3, y);
		result.setElementAt(2, 3, z);
		
		return result;
	}
	
	public static Matrix scaling(float x, float y, float z) {
		Matrix result = Matrix.getIdentityMatrix(4, 4);
		
		result.setElementAt(0, 0, x);
		result.setElementAt(1, 1, y);
		result.setElementAt(2, 2, z);
		
		return result;
	}
	
	public static Matrix reflection(float x, float y, float z) {
		// reflects only on x axis
		return Matrix.scaling(x, y, z);
		
	}
	
	public static Matrix rotationX(float radians) {
		Matrix result = Matrix.getIdentityMatrix(4, 4);
		
		float cosR = (float) Math.cos(radians);
		float sinR = (float) Math.sin(radians);
		
		result.setElementAt(1, 1, cosR);
		result.setElementAt(1, 2, -1 * sinR);
		result.setElementAt(2, 1, sinR);
		result.setElementAt(2, 2, cosR);
		
		return result;
	}

	public static Matrix rotationY(float radians) {
		Matrix result = Matrix.getIdentityMatrix(4, 4);
		
		float cosR = (float) Math.cos(radians);
		float sinR = (float) Math.sin(radians);
		
		result.setElementAt(0, 0, cosR);
		result.setElementAt(0, 2, sinR);
		result.setElementAt(2, 0, -1 * sinR);
		result.setElementAt(2, 2, cosR);
		
		return result;
	}
	
	public static Matrix rotationZ(float radians) {
		Matrix result = Matrix.getIdentityMatrix(4, 4);
		
		float cosR = (float) Math.cos(radians);
		float sinR = (float) Math.sin(radians);
		
		result.setElementAt(0, 0, cosR);
		result.setElementAt(0, 1, -1 * sinR);
		result.setElementAt(1, 0, sinR);
		result.setElementAt(1, 1, cosR);
		
		return result;
	}
	
	public static Matrix shearing(float xy, float xz, float yx, float yz, float zx, float zy) {
		Matrix result = Matrix.getIdentityMatrix(4, 4);
		
		result.setElementAt(0, 1, xy);
		result.setElementAt(0, 2, xz);
		result.setElementAt(1, 0, yx);
		result.setElementAt(1, 2, yz);
		result.setElementAt(2, 0, zx);
		result.setElementAt(2, 1, zy);
		
		return result;
	}
	
	public static Matrix tuple2Matrix(Tuple p) {
		Matrix result = new Matrix(4, 1);
		result.setElementAt(0, 0, p.getX());
		result.setElementAt(1, 0, p.getY());
		result.setElementAt(2, 0, p.getZ());
		result.setElementAt(3, 0, p.getW());
		
		return result;
	}
	
	public static Point matrix2Point(Matrix m) {
		Point result = new Point();
		result.setX(m.getElementAt(0, 0));
		result.setY(m.getElementAt(1, 0));
		result.setZ(m.getElementAt(2, 0));
		result.setW(m.getElementAt(3, 0));

		return result;
	}

	public static Vector matrix2Vector(Matrix m) {
		Vector result = new Vector(0, 0, 0);
		result.setX(m.getElementAt(0, 0));
		result.setY(m.getElementAt(1, 0));
		result.setZ(m.getElementAt(2, 0));
		result.setW(m.getElementAt(3, 0));
		
		return result;
	}

	public Matrix translate(float x, float y, float z) {
		Matrix translationMatrix = Matrix.translation(x, y, z);
		return Matrix.multiply(translationMatrix, this);
	}
	
	public Matrix rotateX(float radians) {
		Matrix rotationMatrix = Matrix.rotationX(radians);
		return Matrix.multiply(rotationMatrix, this);
	}

	public Matrix rotateY(float radians) {
		Matrix rotationMatrix = Matrix.rotationY(radians);
		return Matrix.multiply(rotationMatrix, this);
	}
	
	public Matrix rotateZ(float radians) {
		Matrix rotationMatrix = Matrix.rotationZ(radians);
		return Matrix.multiply(rotationMatrix, this);
	}
	
	public Matrix scale(float x, float y, float z) {
		Matrix scalingMatrix = Matrix.scaling(x, y, z);
		return Matrix.multiply(scalingMatrix, this);
	}
	
	public Matrix shear(float xy, float xz, float yx, float yz, float zx, float zy) {
		Matrix shearingMatrix = Matrix.shearing(xy, xz, yx, yz, zx, zy);
		return Matrix.multiply(shearingMatrix, this);
	}
	
	public static Matrix getViewTransform(Point from, Point to, Vector up) {
		Matrix result = Matrix.getIdentityMatrix(4, 4);
		
		Vector forward = Vector.normalize(to.subtract(from));
		Vector left = Vector.cross(forward, Vector.normalize(up));
		Vector trueUp = Vector.cross(left, forward);
		
		result.setElementAt(0, 0, left.getX());
		result.setElementAt(0, 1, left.getY());
		result.setElementAt(0, 2, left.getZ());
		result.setElementAt(1, 0, trueUp.getX());
		result.setElementAt(1, 1, trueUp.getY());
		result.setElementAt(1, 2, trueUp.getZ());
		result.setElementAt(2, 0, -forward.getX());
		result.setElementAt(2, 1, -forward.getY());
		result.setElementAt(2, 2, -forward.getZ());
		
		result = Matrix.multiply(result, Matrix.translation(-from.getX(), -from.getY(), -from.getZ()));
		
		return result;
	}









}

































