package com.nab.assignment.customermanagement.exception;

/**
 * @author Monalisa Sethi
 *
 */
public class CreditRatingOutOfRangeException extends Exception {

	private static final long serialVersionUID = 1L;

	public CreditRatingOutOfRangeException(String message) {
		super(message);
	}

	public CreditRatingOutOfRangeException(String message, Throwable cause) {
		super(message, cause);
	}

	public CreditRatingOutOfRangeException(Throwable cause) {
		super(cause);
	}
}
