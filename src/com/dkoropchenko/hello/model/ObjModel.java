package com.dkoropchenko.hello.model;

import java.util.List;

import com.dkoropchenko.hello.ctrl.dao.Content;
import com.dkoropchenko.hello.ctrl.dao.DAOFactory;

public class ObjModel {
	
	private DAOFactory daoFactory;
	private Content content;
	
	private static volatile ObjModel instance;
    
	private ObjModel() {
		daoFactory = DAOFactory.getDAOFactory(DAOFactory.ORA);
		content = daoFactory.getContent();
	}
	
	public HelloObj getTop() {
		return content.getData();
 	}
	
	public 
	
    public static ObjModel getInstance() {
    	ObjModel localInstance = instance;
    	if (localInstance == null) {
	        synchronized (ObjModel.class) {
	            localInstance = instance;
	            if (localInstance == null) {
	                instance = localInstance = new ObjModel();
	            }
	        }
    	}
    	return localInstance;
    }
}
