package in.a93;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ObjFile {
	private static final Pattern pattern = Pattern.compile("[v|f] (-?[0-9. ]+){3}");
	private static final Pattern groupPattern = Pattern.compile("g [A-Za-z0-9]*");
	private Group defaultGroup;
	private ArrayList<Group> groups = new ArrayList<Group>();
	private Path filePath;
	
	public ObjFile(Path filePath) {
		this.filePath = filePath;
		this.defaultGroup = new Group();
		this.groups.add(defaultGroup);
	}
	
	public ArrayList<Point> parseObjFile() {
		// TODO: Refactor this function
		ArrayList<Point> retval = new ArrayList<Point>();
		
		Charset charset = Charset.forName("utf-8");
		try (BufferedReader reader = Files.newBufferedReader(this.filePath, charset)) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		        Matcher match = pattern.matcher(line);
		        Matcher matchGroup = groupPattern.matcher(line);
		        
		        if (matchGroup.matches()) {
		        	Group newGroup = new Group();
		        	this.groups.add(newGroup);
		        	
		        } else if (match.matches()) {
		        	String[] lineValues = line.split(" ");
		        	if (lineValues[0].equals("v")) {
		        		float x =  Float.valueOf(lineValues[1]).floatValue();
		        		float y =  Float.valueOf(lineValues[2]).floatValue();
		        		float z =  Float.valueOf(lineValues[3]).floatValue();
		        		retval.add(new Point(x, y, z));
		        	} else if (lineValues[0].equals("f")) {
		        		Point[] points = new Point[lineValues.length - 1];
		        		
		        		for (int i = 1; i < lineValues.length; i++) {
		        			int p = Integer.valueOf(lineValues[i]).intValue();
		        			
		        			// Elements in points need to start from 0 and i starts from 1; hence, i-1
		        			// In the OBJ file, faces assume that the point array has 1 based index; hence, p - 1 in retval
		        			points[i-1] = retval.get(p - 1);
		        		}
		        		
		        		Triangle[] triangles = parsePolygon(points);
		        		
		        		Group latestGroup = this.groups.get(this.groups.size() - 1);
		        		for (Triangle t : triangles) {
		        			latestGroup.addChild(t);		        			
		        		}
		        	}
		        } 
		    }
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
		
		
		return retval;
	}

	public Group objectToGroup() {
		Group retval = new Group();
		
		for (Group g : this.groups) {
			retval.addChild(g);
		}
		
		return retval;
	}
	
	private Triangle[] parsePolygon(Point[] points) {
		Triangle[] retval = new Triangle[points.length - 2]; // https://en.wikipedia.org/wiki/Fan_triangulation
		//  pick one starting vertex, a
		Point startingVertex = points[0];
		
		// create a triangle by combining it with the next two vertices in the list, b and c
		//  starting each triangle with vertex a, adding the last vertex of the previous triangle and the next vertex in the list
		for (int i = 1; i < points.length - 1; i++) {
			Triangle t = new Triangle(startingVertex, points[i], points[i + 1]);
			retval[i - 1] = t;
		}
		
		return retval;
	}
	
	public Group getDefaultGroup() {
		return defaultGroup;
	}

	public ArrayList<Group> getGroups() {
		return groups;
	}
	
	
}
