package com.dkoropchenko.hello.model.hobj;

import java.io.Serializable;

public class HelloObj implements Serializable{

	private int id;
	private String name;
	private int parent_id;
	
	private static final long serialVersionUID = 1L;
	
	public HelloObj(int id, String name, int parent_id) {
		this.id = id;
		this.name = name;
		this.parent_id = parent_id;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getParentId() {
		return parent_id;
	}
}
