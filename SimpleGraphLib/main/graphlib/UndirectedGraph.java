package graphlib;

public class UndirectedGraph<T> extends DirectedGraph<T> {
	
	@Override
    public void addEdge(T source, T destination, int weight) throws EdgeAlreadyExistsException {
		super.addEdge(source, destination, weight);
		
		//As this is undirected graph, add same edge from destination to source
		super.addEdge(destination, source, weight);
	}
}
