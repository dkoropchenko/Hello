package com.dkoropchenko.hello.ctrl.dao.ora;

import com.dkoropchenko.hello.ctrl.dao.Content;
import com.dkoropchenko.hello.ctrl.dao.DAOFactory;

public class OraDAOFactory extends DAOFactory {

	@Override
	public Content getContent() {
		return new OraContent();
	}
	
	
}
