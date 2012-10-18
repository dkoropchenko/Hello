package com.dkoropchenko.hello.model;

import java.io.Serializable;
import java.util.Map;

import com.dkoropchenko.hello.model.attr.*;
import com.dkoropchenko.hello.model.except.InvalidHelloObj;

@SuppressWarnings("rawtypes")
public class HelloObj implements Serializable {

	private int id;
	private int parent_id;
	private HelloType objType;
	
	private static final long serialVersionUID = 1L;
	
	public HelloObj(int id, int parent_id, HelloType objType) throws InvalidHelloObj {
		this.id = id;
		this.parent_id = parent_id;
		this.objType = objType;
	}
	
	public int getId() {
		return id;
	}
	
	public Attribute getAttribute(String name) {
		return objType.getAttribute(name);
	}

	public Map<String,Attribute> getAttributes() {
		return objType.getAttributes();
	}
	
	public int getParentId() {
		return parent_id;
	}
}
