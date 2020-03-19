package graphlib;

import org.junit.Assert;
import org.junit.Test;

public class TraverseGraphTest {	
	
	@Test
	public void testGraphTraverseFunction() {
		IGraph<MyGraphVertex> g = new UndirectedGraph<MyGraphVertex>();
		
		for(int i = 1; i <= 100; i++) {
			g.addVertex(new MyGraphVertex(i, "Vertex" + Integer.toString(i)));
		}
		
		g.traverseGraph( v -> v.setLabel("New" + v.getLabel()));		
		g.traverseGraph(v -> Assert.assertTrue(v.getLabel().startsWith("NewVertex")));
	}
}
