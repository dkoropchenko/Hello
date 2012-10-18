package com.dkoropchenko.hello.ctrl;

import java.util.HashMap;
import java.util.Map;

import com.dkoropchenko.hello.ctrl.action.*;

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
		} catch (IllegalAccessException e) {
			throw new RuntimeException(getClass() + " was unable to access to an action named '" + actionName + "'.");
		} catch (InstantiationException e) {
			throw new RuntimeException(getClass() + " was unable to instantiate an action named '" + actionName + "'.");
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

		return map;
	}
}
