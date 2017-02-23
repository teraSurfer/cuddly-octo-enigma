package stack;

public class StackUnderflowException extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5136376839441927465L;
	public StackUnderflowException(String message){
		super(message);
	}
}
