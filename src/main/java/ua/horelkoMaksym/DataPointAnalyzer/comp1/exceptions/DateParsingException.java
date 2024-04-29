package ua.horelkoMaksym.DataPointAnalyzer.comp1.exceptions;



public class DateParsingException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DateParsingException(String message, Throwable cause) {
		super(message, cause);
	}

	public DateParsingException(String message) {
		super(message);
	}
	
	public DateParsingException(Throwable cause) {
		super(cause);
	}
}
