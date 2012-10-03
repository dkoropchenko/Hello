package com.dkoropchenko.hello.model.dao.ora;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.dkoropchenko.hello.model.dao.Content;
import com.dkoropchenko.hello.model.obj.HelloObj;

public class OraContent extends ORAExecutor implements Content {

	private List<HelloObj> map = new ArrayList<HelloObj>();
	
	private final String SQL_TAB_ITEMS = "items";
	
	private final String SQL_ID_ITEMS = "id_items";
	private final String SQL_NAME_ITEMS = "name_items";
	private final String SQL_ID_PARENT = "parent_id";
	
	private final String SQL_GET_DATA = "SELECT " + SQL_ID_ITEMS + "," + SQL_NAME_ITEMS + "," + SQL_ID_PARENT + " FROM items ORDER BY id_items";
	private final String SQL_ADD_DATA = "INSERT INTO " + SQL_TAB_ITEMS + " (" + SQL_NAME_ITEMS + "," + SQL_ID_PARENT + ") VALUES(?,?)";
	private final String SQL_DEL_DATA = "DELETE FROM " + SQL_TAB_ITEMS + " WHERE " + SQL_ID_ITEMS + "= ?";
	private final String SQL_EDT_NAME_DATA = "UPDATE " + SQL_TAB_ITEMS + " SET " + SQL_NAME_ITEMS + "= ?  WHERE " + SQL_ID_ITEMS + "= ?";
	private final String SQL_EDT_PARENT_DATA = "UPDATE " + SQL_TAB_ITEMS + " SET " + SQL_ID_PARENT + "= ?  WHERE " + SQL_ID_ITEMS + "= ?";
	
	static {
		log = Logger.getLogger(OraContent.class);
	}
	
	public List<HelloObj> getData() {
		execute(SQL_GET_DATA, null);
		return map;
	}
	
	public List<HelloObj> addNew(String content, int parent_id) {
		Map<Integer,Object> tmp = new LinkedHashMap<Integer,Object>();
		
		tmp.put(Types.VARCHAR, content);
		tmp.put(Types.INTEGER, parent_id);
		
		execute(SQL_ADD_DATA,tmp);
		execute(SQL_GET_DATA, null);
		return map;
	}
	
	public List<HelloObj> editName(int id, String content) {
		Map<Integer,Object> tmp = new LinkedHashMap<Integer,Object>();
		
		tmp.put(Types.VARCHAR, content);
		tmp.put(Types.INTEGER, id);
		
		execute(SQL_EDT_NAME_DATA, tmp);
		execute(SQL_GET_DATA, null);
		return map;
	}
	
	public List<HelloObj> editParent(int id, int content) {
		Map<Integer,Object> tmp = new LinkedHashMap<Integer,Object>();
		
		tmp.put(Types.INTEGER, content);
		tmp.put(Types.INTEGER, id);
		
		execute(SQL_EDT_PARENT_DATA, tmp);
		execute(SQL_GET_DATA, null);
		return map;
	}
	
	public List<HelloObj> delete(int id) {
		Map<Integer,Object> tmp = new LinkedHashMap<Integer,Object>();
		tmp.put(Types.INTEGER, id);
		execute(SQL_DEL_DATA, tmp);
		execute(SQL_GET_DATA, null);
		return map;
	}
	
	@Override
	protected void getResult(ResultSet result) throws SQLException {
		if (result == null) return;
		while (result.next()) {
			map.add(new HelloObj(result.getInt(SQL_ID_ITEMS), 
								 result.getString(SQL_NAME_ITEMS), 
								 result.getInt(SQL_ID_PARENT)));
		}
		log.info("List: " + map);
	}

}
