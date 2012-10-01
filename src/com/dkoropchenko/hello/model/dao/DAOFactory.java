package com.dkoropchenko.hello.model.dao;

import com.dkoropchenko.hello.model.dao.ora.OraDAOFactory;

public abstract class DAOFactory {

	public static final int ORA = 1;
	
	public abstract Content getContent();
	
	public static DAOFactory getDAOFactory(int whichFactory) {
		switch (whichFactory) {
			case ORA: 
				return new OraDAOFactory();
			default: 
				return null;
		}
	}
}
