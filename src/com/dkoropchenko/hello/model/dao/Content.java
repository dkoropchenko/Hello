package com.dkoropchenko.hello.model.dao;

import java.util.List;

import com.dkoropchenko.hello.model.obj.HelloObj;

public interface Content {

	List<HelloObj> getData();
	List<HelloObj> addNew(String content, int parent_id);
	List<HelloObj> editName(int id, String content);
	List<HelloObj> editParent(int id, int content);
	List<HelloObj> delete(int id);
}
