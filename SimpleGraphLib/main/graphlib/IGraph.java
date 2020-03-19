package graphlib;

import java.util.LinkedList;
import java.util.function.Consumer;

public interface IGraph<T> {
	
	//adds vertex to the graph
	void addVertex(T s);
	
	//adds edge to the graph
	void addEdge(T source, T destination);
	
	//adds weighted edge to the graph
	void addEdge(T source, T destination, int weight);
	
	//returns a list of edges between 2 vertices 
	LinkedList<Edge<T>> getPath(T source, T destination);
	
	//Traverse function that takes a user defined function and applies it on every vertex of the graph.
    void traverseGraph(Consumer<T> userDefinedFunction);
}

