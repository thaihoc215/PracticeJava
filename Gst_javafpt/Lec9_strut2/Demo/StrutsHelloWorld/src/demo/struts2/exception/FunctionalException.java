package demo.struts2.exception;

public class FunctionalException extends ABSDException {
	private static final String MESSAGE = "exception.functional";
	
	public FunctionalException() {
		super(MESSAGE);
	}

	public FunctionalException(Throwable cause) {
		super(MESSAGE, cause);
	}

	public FunctionalException(String mess) {
		super(mess);
	}
}
