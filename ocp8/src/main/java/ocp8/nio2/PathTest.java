package ocp8.nio2;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathTest {
	public static void main(String[] args) throws URISyntaxException {
//		test1();
//		testResolve1();
//		testResolve2();
		testGetParent();
	}
	
	public static void test1() throws URISyntaxException {
		Path path1 = Paths.get("abc/123");
		System.out.println("path1: " + path1);
		
		Path path2 = Paths.get(new URI("file:///google.com"));
		System.out.println("path2: " + path2);
	}
	
	/**
	 * test resolve(path) where inputted path is an absolute path
	 * 
	 */
	public static void testResolve1(){
		Path path1 = Paths.get("abc/123");
		Path path2 = Paths.get("/test1/test2");
		System.out.println("path1.resolve(path2): " + path1.resolve(path2));
	}
	
	/**
	 * test resolving relative-relative paths
	 */
	public static void testResolve2(){
		Path path1 = Paths.get("abc/123");
		Path path2 = Paths.get("test1/test2");
		System.out.println("path1.resolve(path2): " + path1.resolve(path2));
	}
	
	public static void testGetParent() {
		Path path1 = Paths.get("/test");
		Path path2 = Paths.get("/test/..");
		System.out.println(path1 + ").getParent(): " + path1.getParent());
		System.out.println(path2 + ").getParent(): " + path2.getParent());
	}
}
