package assignment1;

public class SyntaxErrorException extends Exception{

	public SyntaxErrorException(String message) {
		/**
		 * Constructs an object with specific message
		 * @param message A string literal specifying the details of this exception
		 */
		super(message);
	}

}
