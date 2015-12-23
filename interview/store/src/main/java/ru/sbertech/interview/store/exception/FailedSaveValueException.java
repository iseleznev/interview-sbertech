package ru.sbertech.interview.store.exception;

public class FailedSaveValueException extends RuntimeException {
	
	private static final long serialVersionUID = 4692192739518878110L;

	public FailedSaveValueException() {
		super();
	}
	
	public FailedSaveValueException(String explanation) {
		super(explanation);
	}

}
