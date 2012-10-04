package com.dkoropchenko.hello.model.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	
	String OBJECTS = "objects";
	String PARENT_LINK = "parent_link";
	
	String perform(HttpServletRequest request, HttpServletResponse response);
}
