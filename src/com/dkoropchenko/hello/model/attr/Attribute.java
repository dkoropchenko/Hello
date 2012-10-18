package com.dkoropchenko.hello.model.attr;

public class Attribute<T> {
	
	protected String name;
	protected T value; 
	
	public Attribute(String name) {
		this(name, null);
	}
	
	public Attribute(String name, T value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	
	public T getValue() {
		return value;
	}
	
	public void setValue(T value) {
		this.value = value;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this.getClass() == obj.getClass() 
				&& this.hashCode() == obj.hashCode()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		int hc0 = 1;
		int hc1 = (this.name == null ? 0 : this.name.hashCode());
		return hc0*31 + hc1;
	}
}
