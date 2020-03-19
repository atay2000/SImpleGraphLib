package graphlib;

import static org.junit.Assert.*;

import org.junit.Test;

public class GraphPathFindTest {

	@Test
	public void testPathFindDir() {
		IGraph<MyGraphVertex> g = new DirectedGraph<MyGraphVertex>();
		
        MyGraphVertex mgv1 = new MyGraphVertex(1, "vertex1");
        MyGraphVertex mgv2 = new MyGraphVertex(2, "vertex2");
        MyGraphVertex mgv3 = new MyGraphVertex(3, "vertex3");
        MyGraphVertex mgv4 = new MyGraphVertex(4, "vertex4");
        MyGraphVertex mgv5 = new MyGraphVertex(5, "vertex5");        
        MyGraphVertex mgv6 = new MyGraphVertex(6, "vertex6");
        MyGraphVertex mgv7 = new MyGraphVertex(7, "vertex7");
        MyGraphVertex mgv8 = new MyGraphVertex(8, "vertex8");
        
        g.addEdge(mgv1, mgv2);  
    	g.addEdge(mgv1, mgv3);
    	g.addEdge(mgv2, mgv6);  
    	g.addEdge(mgv3, mgv4);  
    	g.addEdge(mgv3, mgv5);  
    	g.addEdge(mgv3, mgv7); 
    	g.addEdge(mgv5, mgv1); 
    	
    	var path = g.getPath(mgv1, mgv7);
    	assertEquals(path.size(), 2);
    	
    	path = g.getPath(mgv7, mgv1);
    	assertNull(path);
    	
    	path = g.getPath(mgv1, mgv1);
    	assertEquals(path.size(), 3);
    	
    	path = g.getPath(mgv6, mgv7);
    	assertNull(path);
    	
    	path = g.getPath(mgv8, mgv1);
    	assertNull(path);
    	
    	path = g.getPath(mgv8, mgv8);
    	assertNull(path);
	}
	
	@Test
	public void testPathFindUndir() {
		IGraph<MyGraphVertex> g = new UndirectedGraph<MyGraphVertex>();
		
        MyGraphVertex mgv1 = new MyGraphVertex(1, "vertex1");
        MyGraphVertex mgv2 = new MyGraphVertex(2, "vertex2");
        MyGraphVertex mgv3 = new MyGraphVertex(3, "vertex3");
        MyGraphVertex mgv4 = new MyGraphVertex(4, "vertex4");
        MyGraphVertex mgv5 = new MyGraphVertex(5, "vertex5");        
        MyGraphVertex mgv6 = new MyGraphVertex(6, "vertex6");
        MyGraphVertex mgv7 = new MyGraphVertex(7, "vertex7");
        MyGraphVertex mgv8 = new MyGraphVertex(8, "vertex8");
        
        g.addEdge(mgv1, mgv2);  
    	g.addEdge(mgv1, mgv3);
    	g.addEdge(mgv2, mgv6);  
    	g.addEdge(mgv3, mgv4);  
    	g.addEdge(mgv3, mgv5);  
    	g.addEdge(mgv3, mgv7); 
    	g.addEdge(mgv5, mgv1); 
    	
    	var path = g.getPath(mgv1, mgv7);
    	assertEquals(path.size(), 2);
    	
    	path = g.getPath(mgv7, mgv1);
    	assertEquals(path.size(), 2);
    	
    	path = g.getPath(mgv1, mgv1);
    	assertEquals(path.size(), 2);
    	
    	path = g.getPath(mgv6, mgv7);
    	assertEquals(path.size(), 4);
    	
    	path = g.getPath(mgv8, mgv1);
    	assertNull(path);
    	
    	path = g.getPath(mgv8, mgv8);
    	assertNull(path);
	}

}
