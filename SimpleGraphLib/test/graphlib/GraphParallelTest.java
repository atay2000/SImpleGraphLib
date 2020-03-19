package graphlib;

import org.hamcrest.core.IsInstanceOf;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.concurrent.*;

public class GraphParallelTest {

	private ArrayList<MyGraphVertex> vertices;
	
	@BeforeClass
    public static void beforeClass() {
        System.out.println("GraphParallelTest: Initial setup...\n");        
    }
	
	@AfterClass
    public static void afterClass() {
        System.out.println("GraphParallelTest: tests completed\n");        
    }
	
	@Before
	public void beforeMethod() {
		System.out.print("Running GraphParallelTest test: "); 
		vertices = new ArrayList<MyGraphVertex>();
		for(int i = 1; i <= 1000; i++) {
			vertices.add(new MyGraphVertex(i, "Vertex" + Integer.toString(i)));
		}
	}
	
	@After
	public void afterMethod() {
		System.out.println("Test completed \n");
	}
	
	
	@Test
	public void testParallelVerticesDefinition() {
		System.out.println("TestParallelVerticesDefinition"); 
		IGraph<MyGraphVertex> g = new DirectedGraph<MyGraphVertex>();		 
		
		CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {
			for(int i = 0; i < 500; i++) {
				g.addVertex(vertices.get(i));
				System.out.println("Added vertex " + vertices.get(i).toString());
			}			
		});
		CompletableFuture<Void> f2 = CompletableFuture.runAsync(() -> {
			for(int i = 500; i < 1000; i++) {
				g.addVertex(vertices.get(i));
				System.out.println("Added vertex " + vertices.get(i).toString());
			}			
		});		
		
		CompletableFuture.allOf(f1,f2).join();		
	}
	
	@Test
	public void testParallelEdgeDefinition() {
		System.out.println("TestParallelEdgeDefinition"); 
		IGraph<MyGraphVertex> g = new UndirectedGraph<MyGraphVertex>();
				
		CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {
			for(int i = 0; i < 499; i++) {
				g.addEdge(vertices.get(i), vertices.get(i+1), i);
				System.out.println("Added edge from " 
						    + vertices.get(i).toString()
							+ "To "
							+ vertices.get(i+1).toString());
			}			
		});
		CompletableFuture<Void> f2 = CompletableFuture.runAsync(() -> {
			for(int i = 500; i < 999; i++) {
				g.addEdge(vertices.get(i), vertices.get(i+1), i);
				System.out.println("Added edge from " 
						    + vertices.get(i).toString()
						    + "To "
						    + vertices.get(i+1).toString());
			}			
		});
		
		CompletableFuture.allOf(f1,f2).join();		
	}
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();	
	@Test
	public void testParallelVertexAlreadyExistsException() {
		System.out.println("TestParallelVertexAlreadyExistsException"); 
		expectedException.expect(CompletionException.class);
		expectedException.expectCause(IsInstanceOf.<Throwable>instanceOf(VertexAlreadyExistsException.class));
		
		IGraph<MyGraphVertex> g = new DirectedGraph<MyGraphVertex>();
		
		CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {
			for(int i = 0; i < 700; i++) {
				g.addVertex(vertices.get(i));				
			}			
		});
		CompletableFuture<Void> f2 = CompletableFuture.runAsync(() -> {
			for(int i = 300; i < 1000; i++) {
				g.addVertex(vertices.get(i));				
			}			
		});
		
		CompletableFuture.allOf(f1,f2).join();
	}
	
	@Test
	public void testParallelEdgeAlreadyExistsException() {
		System.out.println("TestParallelEdgeAlreadyExistsException"); 
		expectedException.expect(CompletionException.class);
		expectedException.expectCause(IsInstanceOf.<Throwable>instanceOf(EdgeAlreadyExistsException.class));
		
		IGraph<MyGraphVertex> g = new DirectedGraph<MyGraphVertex>();
		
		CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {
			for(int i = 0; i < 700; i++) {
				g.addEdge(vertices.get(i), vertices.get(i + 1), 100);			
			}			
		});
		CompletableFuture<Void> f2 = CompletableFuture.runAsync(() -> {
			for(int i = 300; i < 1000; i++) {
				g.addEdge(vertices.get(i), vertices.get(i + 1), 100);					
			}			
		});
		
		CompletableFuture.allOf(f1,f2).join();
	}
}
