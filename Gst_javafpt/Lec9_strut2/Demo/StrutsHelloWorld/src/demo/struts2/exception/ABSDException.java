package demo.struts2.exception;

public class ABSDException extends Exception {
	public ABSDException(String message) {
		super(message);
	}
	
	public ABSDException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
