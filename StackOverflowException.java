package stack;

public class StackOverflowException extends Throwable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1563347688834L;

	public StackOverflowException(String message){
		super(message);
	}
}
