package com.dkoropchenko.hello.model;

import java.util.HashMap;
import java.util.Map;

import com.dkoropchenko.hello.model.action.*;

public class ActionFactory {

	@SuppressWarnings("rawtypes")
	protected Map map = defaultMap();
	
	public ActionFactory() {
		super();
	}
	public Action create(String actionName) {
		@SuppressWarnings("rawtypes")
		Class klass = (Class) map.get(actionName);
		if (klass == null)
			throw new RuntimeException(getClass() + " was unable to find an action named '" + actionName + "'.");
		
		Action actionInstance = null;
		try {
			actionInstance = (Action) klass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return actionInstance;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Map defaultMap() {
		Map map = new HashMap();

		map.put("hello", IndexAction.class);
		map.put("add", AddAction.class);
		map.put("delete", DeleteAction.class);
		map.put("edit", EditAction.class);
		map.put("child", ChildAction.class);
		//map.put("loginAction", LoginAction.class);
		//map.put("logoutAction", LogoutAction.class);

		return map;
	}
}
