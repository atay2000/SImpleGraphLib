package graphlib;

public class EdgeAlreadyExistsException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2130260172089913411L;

	EdgeAlreadyExistsException(String message){
		super(message);
	}
}
