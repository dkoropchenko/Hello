package com.dkoropchenko.hello.model.except;

public class InvalidHelloObj extends Exception {
	
	private static final long serialVersionUID = 1L;

	public InvalidHelloObj() {
		super();
	}
	
	public int getCode() {
		return 1;
	}
}
