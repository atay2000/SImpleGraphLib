package graphlib;

import static org. junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class GraphVertexDefinitionTest {

	@Test
	public void testGraphVertexDefinition() {
		IGraph<MyGraphVertex> g = new DirectedGraph<MyGraphVertex>();
		
		try {
			for(int i = 1; i <= 10000; i++) {
				g.addVertex(new MyGraphVertex(i, "Vertex" + Integer.toString(i)));
			}
		}
		catch(Exception ex) {
			fail("Exception occured: " + ex.getMessage());
		}		
	}
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();	
	@Test
	public void testVertexAlreadyExistsException() {
		expectedException.expect(VertexAlreadyExistsException.class);
		
		IGraph<MyGraphVertex> g = new DirectedGraph<MyGraphVertex>();
		MyGraphVertex v = new MyGraphVertex(1, "TestVertex");
		
		g.addVertex(v);
		g.addVertex(v);
	}

}
