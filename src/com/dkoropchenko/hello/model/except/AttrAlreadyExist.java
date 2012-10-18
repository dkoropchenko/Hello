package com.dkoropchenko.hello.model.except;

public class AttrAlreadyExist extends Exception {

	private static final long serialVersionUID = 1L;

	public AttrAlreadyExist() {
		super();
	}
	
	public int getCode() {
		return 1;
	}
}
