package graphlib;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

public class GraphEdgeDefinitionTest {

	private ArrayList<MyGraphVertex> vertices;
	
	@Before
	public void beforeMethod() {		
		vertices = new ArrayList<MyGraphVertex>();
		for(int i = 1; i <= 10000; i++) {
			vertices.add(new MyGraphVertex(i, "Vertex" + Integer.toString(i)));
		}
	}
	
	@Test
	public void testDirectedGraphEdgeDefinition() {
		IGraph<MyGraphVertex> g = new DirectedGraph<MyGraphVertex>();
		
		try {			
			for(int i = 0; i < 9999; i++) {
				g.addEdge(vertices.get(i), vertices.get(i+1), i);
				g.addEdge(vertices.get(i+1), vertices.get(i), i);
			}
		}
		catch(Exception ex) {
			fail("Exception occured: " + ex.getMessage());
		}		
	}
	
	@Test
	public void testUndirectedGraphEdgeDefinition() {
		IGraph<MyGraphVertex> g = new UndirectedGraph<MyGraphVertex>();
		
		try {
			for(int i = 0; i < 9999; i++) {
				g.addEdge(vertices.get(i), vertices.get(i+1), i);
			}
		}
		catch(Exception ex) {
			fail("Exception occured: " + ex.getMessage());
		}		
	}
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();	
	@Test
	public void testEdgeAlreadyExistsExceptionDir() {
		expectedException.expect(EdgeAlreadyExistsException.class);
		
		IGraph<MyGraphVertex> g = new DirectedGraph<MyGraphVertex>();
		MyGraphVertex v1 = new MyGraphVertex(1, "TestVertex1");
		MyGraphVertex v2 = new MyGraphVertex(2, "TestVertex2");
		
		g.addEdge(v1, v2);
		g.addEdge(v1, v2);
	}
	
	@Test
	public void testEdgeAlreadyExistsExceptionUndir() {
		expectedException.expect(EdgeAlreadyExistsException.class);
		
		IGraph<MyGraphVertex> g = new UndirectedGraph<MyGraphVertex>();
		MyGraphVertex v1 = new MyGraphVertex(1, "TestVertex1");
		MyGraphVertex v2 = new MyGraphVertex(2, "TestVertex2");
		
		g.addEdge(v1, v2);
		g.addEdge(v2, v1);
	}

}
