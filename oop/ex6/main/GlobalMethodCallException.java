
package oop.ex6.main;

/** This exception is thrown when a method is called in the global scope.
 * @author Joshua Voss, shanam
 *
 */
public class GlobalMethodCallException extends SJavacException {

	private static final long serialVersionUID = 1L;
	
	private static final String defaultMsg = "A method was called in the global scope."
			+ "Methods can only be called inside other methods in sJava.";

	/** 
	 * Creates an exception with a default message.
	 */
	public GlobalMethodCallException() {
		super(defaultMsg);
	}

	/** Creates an exception with a custom message.
	 * 
	 * @param msg message to be passed up.
	 */
	public GlobalMethodCallException(String msg) {
		super(msg);
	}

}
