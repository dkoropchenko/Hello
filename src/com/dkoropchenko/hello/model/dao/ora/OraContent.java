package com.dkoropchenko.hello.model.dao.ora;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.dkoropchenko.hello.model.dao.Content;

public class OraContent extends ORAExecutor implements Content {

	private Map<String,String> map = new HashMap<String, String>();

	private final String SQL_GET_DATA = "SELECT id,content FROM hello ORDER BY id";
	private final String SQL_ADD_DATA = "INSERT INTO hello (id,content) VALUES(?, ?)";
	private final String SQL_DEL_DATA = "DELETE FROM hello WHERE id= ?";
	private final String SQL_EDT_DATA = "UPDATE hello SET content= ? WHERE id= ?";
	
	static {
		log = Logger.getLogger(OraContent.class);
	}
	
	public Map<String, String> getData() {
		execute(SQL_GET_DATA, null);
		return map;
	}
	
	public Map<String, String> addNew(String id, String content) {
		execute(SQL_ADD_DATA, new String[] { id, content});
		execute(SQL_GET_DATA, null);
		return map;
	}
	
	public Map<String, String> edit(String id, String content) {
		execute(SQL_EDT_DATA, new String[] { content, id });
		execute(SQL_GET_DATA, null);
		return map;
	}
	
	public Map<String, String> delete(String id) {
		execute(SQL_DEL_DATA, new String[] { id });
		execute(SQL_GET_DATA, null);
		return map;
	}
	
	@Override
	protected void getResult(ResultSet result) throws SQLException {
		if (result == null) return;
		while (result.next()) {
			map.put(result.getString("id"), 
					result.getString("content"));
		}
		log.info("Map: " + map);
	}

}
