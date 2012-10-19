package com.dkoropchenko.hello.model;

import java.util.HashMap;
import java.util.Map;

import com.dkoropchenko.hello.model.attr.Attribute;
import com.dkoropchenko.hello.model.except.AttrAlreadyExist;
import com.dkoropchenko.hello.model.except.AttrNotExist;

@SuppressWarnings("rawtypes")
public class HelloType {

	private String name;
	
	private Map<String,Attribute> attrsTypes;
	
	public HelloType(String name) {
		this(name, new HashMap<String,Attribute>());
	}
	
	public HelloType(String name, Map<String,Attribute> attrsTypes) {
		this.name = name;
		this.attrsTypes = attrsTypes;
	}
	
	public void addAttr(Attribute atr) throws AttrAlreadyExist {
		if (attrsTypes.containsKey(atr.getName())) {
			throw new AttrAlreadyExist();
		}
		attrsTypes.put(atr.getName(), atr);
	}
	
	public void removeAttr(String name) throws AttrNotExist {
		if (attrsTypes.remove(name) == null) {
			throw new AttrNotExist();
		}
	}
	
	public String getName() {
		return name;
	}
	
	public Attribute getAttribute(String name) {
		return attrsTypes.get(name);
	}
	
	public Map<String,Attribute> getAttributes() {
		return attrsTypes;
	}
}
