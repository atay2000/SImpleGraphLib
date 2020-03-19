package graphlib;

public class VertexAlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4922586509509612576L;

	VertexAlreadyExistsException(String message){
		super(message);
	}
}
