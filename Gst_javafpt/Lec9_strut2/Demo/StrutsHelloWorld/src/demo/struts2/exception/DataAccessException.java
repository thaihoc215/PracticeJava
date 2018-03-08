package demo.struts2.exception;

public class DataAccessException extends ABSDException {
	private static final String MESSAGE = "exception.data.access";
	
	public DataAccessException() {
		super(MESSAGE);
	}

	public DataAccessException(Throwable cause) {
		super(MESSAGE, cause);
	}
	
	public DataAccessException(String mess) {
		super(mess);
	}
}
