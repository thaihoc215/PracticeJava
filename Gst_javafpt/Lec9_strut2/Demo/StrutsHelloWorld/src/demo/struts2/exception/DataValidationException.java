package demo.struts2.exception;

public class DataValidationException extends ABSDException {
	private static final String MESSAGE = "exception.data.validation";
	public static final String NULL_PARAM_MESSAGE = "exception.data.validation.nullparam";
	
	public DataValidationException() {
		super(MESSAGE);
	}

	public DataValidationException(String message) {
		super(message);
	}

	public DataValidationException(Throwable cause) {
		super(MESSAGE, cause);
	}

	public DataValidationException(String message, Throwable cause) {
		super(message, cause);
	}
}
