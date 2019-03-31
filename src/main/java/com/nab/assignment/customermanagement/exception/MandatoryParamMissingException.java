package com.nab.assignment.customermanagement.exception;

public class MandatoryParamMissingException extends Exception {

	private static final long serialVersionUID = 1L;

	public MandatoryParamMissingException(String message) {
		super(message);
	}

	public MandatoryParamMissingException(String message, Throwable cause) {
		super(message, cause);
	}

	public MandatoryParamMissingException(Throwable cause) {
		super(cause);
	}
}
