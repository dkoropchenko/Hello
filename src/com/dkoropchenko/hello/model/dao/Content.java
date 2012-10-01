package com.dkoropchenko.hello.model.dao;

import java.util.Map;

public interface Content {

	Map<String,String> getData();
	Map<String,String> addNew(String id, String content);
	Map<String,String> edit(String id, String content);
	Map<String,String> delete(String id);
}
