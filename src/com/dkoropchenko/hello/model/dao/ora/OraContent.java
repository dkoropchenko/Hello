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
import com.dkoropchenko.hello.model.hobj.HelloObj;

public class OraContent extends ORAExecutor implements Content {
	
	private final int SQL_TOP = 5;
	
	private final String SQL_TAB_ITEMS = "items";
	
	private final String SQL_ID_ITEMS = "id_items";
	private final String SQL_NAME_ITEMS = "name_items";
	private final String SQL_ID_PARENT = "parent_id";
	
	
	private final String SQL_GET_DATA = "SELECT " + SQL_ID_ITEMS + "," + SQL_NAME_ITEMS + "," + SQL_ID_PARENT + " FROM items WHERE parent_id = ? ORDER BY id_items";
	//private final String SQL_GET_PARENT = "SELECT " + SQL_ID_ITEMS + "," + SQL_NAME_ITEMS + "," + SQL_ID_PARENT + " FROM items WHERE id_items = ? ORDER BY id_items";
	private final String SQL_GET_PARENT_LINK = "SELECT " + SQL_ID_ITEMS + "," + SQL_NAME_ITEMS + "," + SQL_ID_PARENT + " FROM items START WITH id_items = ? CONNECT BY PRIOR parent_id = id_items";
	
	private final String SQL_ADD_DATA = "INSERT INTO " + SQL_TAB_ITEMS + " (" + SQL_NAME_ITEMS + "," + SQL_ID_PARENT + ") VALUES(?,?)";
	private final String SQL_DEL_DATA = "DELETE FROM " + SQL_TAB_ITEMS + " WHERE " + SQL_ID_ITEMS + "= ?";
	private final String SQL_EDT_NAME_DATA = "UPDATE " + SQL_TAB_ITEMS + " SET " + SQL_NAME_ITEMS + "= ?  WHERE " + SQL_ID_ITEMS + "= ?";
	private final String SQL_EDT_PARENT_DATA = "UPDATE " + SQL_TAB_ITEMS + " SET " + SQL_ID_PARENT + "= ?  WHERE " + SQL_ID_ITEMS + "= ?";
	
	static {
		log = Logger.getLogger(OraContent.class);
	}
	
	public List<HelloObj> getData() {
		return getChildData(SQL_TOP);
	}
	
	public List<HelloObj> getChildData(int parent_id) {
		Map<Integer,Object> tmp = new LinkedHashMap<Integer,Object>();
		
		tmp.put(Types.INTEGER, parent_id);
		
		return execute(SQL_GET_DATA, tmp);
	}
	
	public List<HelloObj> getParentLink() {
		return getParentLink(SQL_TOP);
	}
	
	public List<HelloObj> getParentLink(int id) {
		Map<Integer,Object> tmp = new LinkedHashMap<Integer,Object>();
		
		tmp.put(Types.INTEGER, id);
		
		return execute(SQL_GET_PARENT_LINK, tmp);
	}
	
	public List<HelloObj> addNew(String content, int parent_id) {
		Map<Integer,Object> tmp = new LinkedHashMap<Integer,Object>();
		
		tmp.put(Types.VARCHAR, content);
		tmp.put(Types.INTEGER, parent_id);
		
		execute(SQL_ADD_DATA,tmp);
		return execute(SQL_GET_DATA, null);
	}
	
	public List<HelloObj> editName(int id, String content) {
		Map<Integer,Object> tmp = new LinkedHashMap<Integer,Object>();
		
		tmp.put(Types.VARCHAR, content);
		tmp.put(Types.INTEGER, id);
		
		execute(SQL_EDT_NAME_DATA, tmp);
		return execute(SQL_GET_DATA, null);
	}
	
	public List<HelloObj> editParent(int id, int content) {
		Map<Integer,Object> tmp = new LinkedHashMap<Integer,Object>();
		
		tmp.put(Types.INTEGER, content);
		tmp.put(Types.INTEGER, id);
		
		execute(SQL_EDT_PARENT_DATA, tmp);
		return execute(SQL_GET_DATA, null);
	}
	
	public List<HelloObj> delete(int id) {
		Map<Integer,Object> tmp = new LinkedHashMap<Integer,Object>();
		tmp.put(Types.INTEGER, id);
		execute(SQL_DEL_DATA, tmp);
		return execute(SQL_GET_DATA, null);
	}
	
	@Override
	protected List<HelloObj> getResult(ResultSet result) throws SQLException {
		List<HelloObj> tmp = new ArrayList<HelloObj>();
		if (result == null) return tmp;
		while (result.next()) {
			tmp.add(new HelloObj(result.getInt(SQL_ID_ITEMS), 
								 result.getString(SQL_NAME_ITEMS), 
								 result.getInt(SQL_ID_PARENT)));
		}
		log.info("List: " + tmp);
		return tmp;
	}

}
