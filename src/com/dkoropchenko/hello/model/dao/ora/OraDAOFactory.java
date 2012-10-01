package com.dkoropchenko.hello.model.dao.ora;

import com.dkoropchenko.hello.model.dao.Content;
import com.dkoropchenko.hello.model.dao.DAOFactory;

public class OraDAOFactory extends DAOFactory {

	@Override
	public Content getContent() {
		return new OraContent();
	}
	
	
}
