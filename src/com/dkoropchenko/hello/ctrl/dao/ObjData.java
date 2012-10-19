package com.dkoropchenko.hello.ctrl.dao;

import com.dkoropchenko.hello.model.HelloObj;
import com.dkoropchenko.hello.model.attr.Attribute;

public interface ObjData {

	HelloObj getObject(); // get top obj
	HelloObj getObject(int id); // get obj by id
	HelloObj setNameObj(int id, String name); // set obj name by id
	HelloObj setParentObj(int id, int idParent); // set obj parent id by obj id
	void addObject(HelloObj hobj); // add obj by obj
	void removeObject(int id); // remove obj by id
	@SuppressWarnings("rawtypes")
	void setObjAttr(int idObj, Attribute attr); // add or update attr
}
