package in.a93;

public class Canvas {
	private int width;
	private int height;
	private Color[][] canvasPixelColors;
	
	public Canvas(int width, int height) {
		this.width = width;
		this.height = height;
		this.canvasPixelColors = new Color[width][height];
		initializeCanvas();
	}
	
	private void initializeCanvas() {
		for (int i = 0; i < canvasPixelColors.length; i++) {
			for (int j = 0; j < canvasPixelColors[i].length; j++) {
				canvasPixelColors[i][j] = new Color(0.0f, 0.0f, 0.0f);
			}
		}
	}
	
	public void setCanvasWidth(int width) { this.width = width;	}
	
	public int getCanvasWidth() { return this.width; }

	public void setCanvasHeight(int height) { this.height = height;	}
	
	public int getCanvasHeight() { return this.height; }
	
	public Color getColorAt(int x, int y) {	return canvasPixelColors[x][y];	}
	
	public void setColorAt(int x, int y, Color color) { canvasPixelColors[x][y] = color; }
	
	public static void writePixelAt(Canvas canvas, int xPos, int yPos, Color color) {
		canvas.setColorAt(xPos, yPos, color);
	}
	
	public static Color pixelAt(Canvas canvas, int xPos, int yPos) {
		return canvas.getColorAt(xPos, yPos);
	}
	
	public static String canvasToPpm(Canvas canvas) {
		StringBuilder ppmBuilder = new StringBuilder();
		ppmBuilder.append("P3\n");
		ppmBuilder.append(canvas.getCanvasWidth() + " " + canvas.getCanvasHeight() + "\n");
		ppmBuilder.append("255\n");
		
		// Each element of height is a row with width elements
		for (int j = 0; j < canvas.getCanvasHeight(); j++) {
			for (int i = 0; i < canvas.getCanvasWidth(); i++) {
				float _red = canvas.getColorAt(i, j).getRed();
				float _green = canvas.getColorAt(i, j).getGreen();
				float _blue = canvas.getColorAt(i, j).getBlue();
				
				// clamp between 0 - 255
				_red = (_red * 255 >= 255) ? 255 : ((_red * 255) < 0 ? 0 : _red * 255);
				_green = (_green * 255 >= 255) ? 255 : ((_green * 255) < 0 ? 0 : _green * 255);
				_blue = (_blue * 255 >= 255) ? 255 : ((_blue * 255) < 0 ? 0 : _blue * 255);
				
				String _temp = (int) Math.ceil(_red) + " " + (int) Math.ceil(_green) + " " + (int) Math.ceil(_blue) + " ";
				
				ppmBuilder.append(_temp + "\n");
			}
		}
		return ppmBuilder.toString();
	}
	
	public static void colorSquare(Canvas canvas, int xPos, int yPos, Color color) {
		int xBound = canvas.getCanvasWidth() - 1;
		int yBound = canvas.getCanvasHeight() - 1;
		
		int xPosEnd = ((xPos + 10) >= xBound) ? xBound : (xPos + 10);
		int yPosEnd = ((yPos + 10) >= yBound) ? yBound : (yPos + 10);
		
		for (int i = xPos; i < xPosEnd; i++) {
			for (int j = yPos; j < yPosEnd; j++) {
//				System.out.println(i + ", " + j);
				canvas.setColorAt(i, j, color);
			}
		}
		  
	}
	
}
