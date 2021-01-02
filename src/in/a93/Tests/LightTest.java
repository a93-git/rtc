package in.a93.Tests;

import in.a93.Light;
import in.a93.Point;
import in.a93.Color;


public class LightTest {
	public static void pointLightTest() {
		Color intensity = new Color(1, 1, 1);
		Point position = new Point(0, 0, 0);

		Light light = new Light(position, intensity);
		
		System.out.println("Light position: " + light.getPosition());
		System.out.println("Light intensity: " + light.getIntensity());
	}
}
