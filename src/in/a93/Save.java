package in.a93;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;

public class Save {
	public static String saveToDiskAsByte(String filePath, String stringToSave) {
		
		byte[] data = stringToSave.getBytes();
		Path p = Paths.get(filePath);
		
		try (OutputStream out = new BufferedOutputStream(
		      Files.newOutputStream(p, CREATE, APPEND))) {
		      out.write(data, 0, data.length);
		    } catch (IOException x) {
		      System.err.println(x);
		}
		
		return filePath;
	}

	public static String saveToDiskAsText(String filePath, String stringToSave) {
		
		Charset charset = Charset.forName("US-ASCII");
		Path p = Paths.get(filePath);
		try (BufferedWriter writer = Files.newBufferedWriter(p, charset)) {
		    writer.write(stringToSave, 0, stringToSave.length());
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
		
		return filePath;
	}
}
