package com.dkoropchenko.hello.model.except;

public class AttrNotExist extends Exception {

	private static final long serialVersionUID = 1L;

	public AttrNotExist() {
		super();
	}
	
	public int getCode() {
		return 1;
	}
}
