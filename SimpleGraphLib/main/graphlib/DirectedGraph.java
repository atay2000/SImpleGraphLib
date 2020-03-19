package graphlib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class DirectedGraph<T> implements IGraph<T> {
	// Use Hashmap to store the edges in the graph 
    private Map<T, List<Edge<T>>> map = Collections.synchronizedMap(new HashMap<>());
    
    // This function adds a new vertex to the graph
    public void addVertex(T vertex) { 
        if(map.containsKey(vertex)) {
        	throw new VertexAlreadyExistsException("Vertex: " 
        										   + vertex.toString() 
        										   + " already exists");
        }
    	
    	map.put(vertex, Collections.synchronizedList(new LinkedList<Edge<T>>())); 
    } 
    
    // This function adds the edge 
    // between source to destination 
    public void addEdge(T source, T destination) {   
        addEdge(source, destination, 0);
    }
    
    // This function adds the edge 
    // between source to destination with weight
    public void addEdge(T source, T destination, int weight) {
    	  	
    	if (!map.containsKey(source)) 
    		addVertex(source); 
  
    	if (!map.containsKey(destination)) 
    		addVertex(destination);
    	        
        if(map.get(source).stream().anyMatch(e -> e.getTo() == destination)) {
        	throw new EdgeAlreadyExistsException("Edge between: " + source.toString()
        										 + " and: " + destination.toString()
        										 + " already exists");        	
        }
        
        map.get(source).add(new Edge<T>(source, destination, weight));
    }
    
    //Traverse function that takes a user defined function 
    //and applies it on every vertex of the graph.
    public void traverseGraph(Consumer<T> userDefinedFunction) {
    	for(T v: map.keySet()) {
    		userDefinedFunction.accept(v); 
    	}
    }
    
    private Boolean pathExists(T source, T destination, 
    		ArrayList<T> visited, LinkedList<Edge<T>> edges) {    	
    	
    	visited.add(source);
    	
    	if(source == destination)
    		return true;
    	
    	if(map.get(source) != null) {
    		var edgesToUnvisited = map.get(source).stream().filter(e -> !visited.contains(e.getTo()))
    				.collect(Collectors.toList());
    	
    		for(Edge<T> edge : edgesToUnvisited) {    		
    			if(pathExists(edge.getTo(), destination, visited, edges)) {
    				edges.push(edge);
    				return true;
    			}
    		}
    	}
    	
    	return false;
    }
    
	public LinkedList<Edge<T>> getPath(T source, T destination) {
		
		LinkedList<Edge<T>> edges = new LinkedList<>();			
		ArrayList<T> visited = new ArrayList<>();
		
		if(source == destination) {
			//Need to find path from vertex to itself; to do that,
			//look for path from vertex's adjacent nodes to vertex
			//then add edge from the vertex to appropriate adjacent one
			var edgesToAdjacent = map.get(source);
			
			if(edgesToAdjacent != null) {
				for(Edge<T> edge : edgesToAdjacent) {
					if(pathExists(edge.getTo(), destination, visited, edges)) {
						edges.push(edge);
						return edges;
					}
					else {
						visited.clear();
						edges.clear();
					}
				}
			}
			return null;
		}
		else {
			//Finding path between two different vertices
			if(pathExists(source, destination, visited, edges))
				return edges;
			else
				return null;
		}
	}
}
