package in.a93.Tests;

import java.nio.file.Path;
import java.util.ArrayList;

import in.a93.*;

public class ObjFileTest {
//	public static void test1() {
//		Path filePath = Path.of("C:\\Users\\win10\\Desktop\\gibberish.txt");
//		ObjFile o = new ObjFile(filePath);
//		ArrayList<Point> a = o.parseObjFile();
//		if (a == null) System.out.println("Gibberish file");
//		else {
//			for (Point p : a) {
//				System.out.println(p);
//			}			
//		}
//		
//	}
//
//	public static void test2() {
//		Path filePath = Path.of("C:\\Users\\win10\\Desktop\\input2.txt");
//		ObjFile o = new ObjFile(filePath);
//		ArrayList<Point> a = o.parseObjFile();
//		if (a == null) System.out.println("Gibberish file");
//		else {
//				System.out.println(a);			
//		}
//		
//	}
//	
//	public static void test3() {
//		Path filePath = Path.of("C:\\Users\\win10\\Desktop\\input3.txt");
//		ObjFile o = new ObjFile(filePath);
//		ArrayList<Point> a = o.parseObjFile();
//		if (a == null) System.out.println("Gibberish file");
//		else {
//			System.out.println(a);
//			
//			for (int i = 0; i < o.getDefaultGroup().getChildren().size(); i++) {
//				System.out.println("This child is of type: " + o.getDefaultGroup().getChildren().get(i).getClass());
//				if (o.getDefaultGroup().getChildren().get(i) instanceof Triangle) { // Always perform this check before downcasting
//					Triangle t = (Triangle) o.getDefaultGroup().getChildren().get(i);
//					System.out.println("Its vertices are: " + t.getP1());
//					System.out.println(t.getP2());
//					System.out.println(t.getP3());
//					
//				}
//			}
//		}
//		
//	}
//	
//	public static void test4() {
//		Path filePath = Path.of("C:\\Users\\win10\\Desktop\\input4.txt");
//		ObjFile o = new ObjFile(filePath);
//		ArrayList<Point> a = o.parseObjFile();
//		if (a == null) System.out.println("Gibberish file");
//		else {
//			System.out.println(a);
//			
//			for (int i = 0; i < o.getDefaultGroup().getChildren().size(); i++) {
//				System.out.println("This child is of type: " + o.getDefaultGroup().getChildren().get(i).getClass());
//				if (o.getDefaultGroup().getChildren().get(i) instanceof Triangle) { // Always perform this check before downcasting
//					Triangle t = (Triangle) o.getDefaultGroup().getChildren().get(i);
//					System.out.println("Its vertices are: " + t.getP1());
//					System.out.println(t.getP2());
//					System.out.println(t.getP3());
//					
//				}
//			}
//		}
//		
//	}
//	
//	public static void test5() {
//		Path filePath = Path.of("C:\\Users\\win10\\Desktop\\triangles.obj");
//		ObjFile o = new ObjFile(filePath);
//		o.parseObjFile();
//		if (a == null) System.out.println("Gibberish file");
//		else {
//			System.out.println(a);
//			
//			for (int j = 0; j < o.getGroups().size(); j++) {
//				for (int i = 0; i < o.getGroups().get(j).getChildren().size(); i++) {
//					System.out.println("This child belongs to group: " + (j+1));
//					System.out.println("This child is of type: " + o.getGroups().get(j).getChildren().get(i).getClass());
//					
//					if (o.getGroups().get(j).getChildren().get(i) instanceof Triangle) { // Always perform this check before downcasting
//						Triangle t = (Triangle) o.getGroups().get(j).getChildren().get(i);
//						System.out.println("Its vertices are: " + t.getP1());
//						System.out.println(t.getP2());
//						System.out.println(t.getP3());
//						
//					}
//				}				
//			}
//		}
//	}
//
//	public static void test6() {
//		Path filePath = Path.of("C:\\Users\\win10\\Desktop\\teddy.obj");
//		ObjFile o = new ObjFile(filePath);
//		ArrayList<Point> a = o.parseObjFile();
//		if (a == null) System.out.println("Gibberish file");
//		else {
//			System.out.println(a);
//			
//			for (int j = 0; j < o.getGroups().size(); j++) {
//				for (int i = 0; i < o.getGroups().get(j).getChildren().size(); i++) {
//					System.out.println("This child belongs to group: " + (j+1));
//					System.out.println("This child is of type: " + o.getGroups().get(j).getChildren().get(i).getClass());
//					
//					if (o.getGroups().get(j).getChildren().get(i) instanceof Triangle) { // Always perform this check before downcasting
//						Triangle t = (Triangle) o.getGroups().get(j).getChildren().get(i);
//						System.out.println("Its vertices are: " + t.getP1());
//						System.out.println(t.getP2());
//						System.out.println(t.getP3());
//						
//					}
//				}				
//			}
//		}
//	}
	
	public static void test7() {
		Path filePath = Path.of("C:\\Users\\win10\\Desktop\\vn.obj");
		ObjFile o = new ObjFile(filePath);
		o.parseObjFile();
		
		for (int j = 0; j < o.getGroups().size(); j++) {
			for (int i = 0; i < o.getGroups().get(j).getChildren().size(); i++) {
				System.out.println("This child belongs to group: " + (j+1));
				System.out.println("This child is of type: " + o.getGroups().get(j).getChildren().get(i).getClass());
				
				if (o.getGroups().get(j).getChildren().get(i) instanceof SmoothTriangle) { // Always perform this check before downcasting
					SmoothTriangle t = (SmoothTriangle) o.getGroups().get(j).getChildren().get(i);
					System.out.println("Its vertices are: " + t.getP1());
					System.out.println(t.getP2());
					System.out.println(t.getP3());
					System.out.println("Its normals are: " + t.getN1());
					System.out.println(t.getN2());
					System.out.println(t.getN3());
				}
			}				
		}

	}
	
}
