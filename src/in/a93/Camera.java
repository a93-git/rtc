package in.a93;

import java.util.ArrayList;
import java.lang.Thread;

import in.a93.Exceptions.MatrixNotInvertibleException;

public class Camera {
	private int hsize;
	private int vsize;
	private float fieldOfView;
	private Matrix transform;
	private float pixelSize;
	private float halfWidth;
	private float halfHeight;
	private static float DELTA = 0.00001f;

	public Camera(int hsize, int vsize, float fieldOfView) {
		this.hsize = hsize;
		this.vsize = vsize;
		this.fieldOfView = fieldOfView;
		this.transform = Matrix.getIdentityMatrix(4, 4);
		
		float halfView = (float) Math.tan(fieldOfView / 2);
		
		
		// We need to cast the integers to float
		// This took me 16 hours to debug :)
		float aspect = (float) hsize / (float) vsize;
		
		
		if ((aspect > 1) || ((float) Math.abs(aspect - 1) < Camera.DELTA)) {
			this.halfWidth = halfView;
			this.halfHeight = halfView / aspect;
		} else {
			this.halfWidth = halfView * aspect;
			this.halfHeight = halfView;
		}
		this.pixelSize = (this.halfWidth * 2) / hsize;
	}
	
	public Ray rayForPixel(int xPos, int yPos) {
		float xOffset = (xPos + 0.5f) * this.getPixelSize();
		float yOffset = (yPos + 0.5f) * this.getPixelSize();
		
		float worldX = this.getHalfWidth() - xOffset;
		float worldY = this.getHalfHeight() - yOffset;
		
		Point pixel = null;
		Point origin = null;
		Vector direction = null;
		Matrix invTransform = null;
		
		try {
			if (Matrix.inverseMatMap.containsKey(this.getTransform())) invTransform = Matrix.inverseMatMap.get(this.getTransform());
			else invTransform = Matrix.getInverseMatrix(this.getTransform());
		} catch (MatrixNotInvertibleException e) {
			e.printStackTrace();
		}
		
		pixel = Matrix.matrix2Point(Matrix.multiply(invTransform, Matrix.tuple2Matrix(new Point(worldX, worldY, -1))));
		origin = Matrix.matrix2Point(Matrix.multiply(invTransform, Matrix.tuple2Matrix(new Point(0, 0, 0))));

		direction = Vector.normalize(pixel.subtract(origin));
		
		return new Ray(origin, direction);
	}
	

	
	public Canvas render(World w, int recursionDepth) {
		Canvas image = new Canvas(this.getHsize(), this.getVsize());
		
		for (int y = 0; y < this.getVsize(); y++) {
			for (int x = 0; x < this.getHsize(); x++) {
//				System.out.println("Processing ray: " + (x+1) + ", " + (y+1));
				Ray ray = this.rayForPixel(x, y);
				Color color = World.getColorAt(w, ray, recursionDepth);
				image.setColorAt(x, y, color);
			}
		}
		
		return image;
	}
	
	public float getPixelSize() { return this.pixelSize; }
	public float getHalfWidth() { return this.halfWidth; }
	public float getHalfHeight() { return this.halfHeight; }
	public Matrix getTransform() { return this.transform; }
	
//	public void setTransform(Matrix transform) { this.transform = transform; this.updateTransformInverse();}
	public void setTransform(Matrix transform) { this.transform = transform;}

	public int getHsize() {
		return hsize;
	}

	public void setHsize(int hsize) {
		this.hsize = hsize;
	}

	public int getVsize() {
		return vsize;
	}

	public void setVsize(int vsize) {
		this.vsize = vsize;
	}

	public float getFieldOfView() {
		return fieldOfView;
	}

	public void setFieldOfView(float fieldOfView) {
		this.fieldOfView = fieldOfView;
	}
}
