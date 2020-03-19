package graphlib;

public class Edge<T> {
	private T from;
	private T to; 
    private int weight;
    
    public Edge(T from, T to, int weight) {        
        this.from = from;
    	this.to = to;
        this.weight = weight;
    }

    T getFrom() {
    	return from;
    }
    
    T getTo() {
        return to;
    }

    int getWeight() {
        return weight;
    }   
}
