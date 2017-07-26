package com.kansate.common.exception;

public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ApplicationException() {
	}

	public ApplicationException(Throwable e) {
		super(e);
	}

	public ApplicationException(String message) {
		super(message);
	}

	public ApplicationException(Throwable e, String message) {
		super(message, e);
	}
}
