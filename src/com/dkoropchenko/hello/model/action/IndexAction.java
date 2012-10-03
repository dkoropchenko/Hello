package com.dkoropchenko.hello.model.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.dkoropchenko.hello.model.dao.DAOFactory;
import com.dkoropchenko.hello.model.obj.HelloObj;

public class IndexAction implements Action {

	private static Logger log = Logger.getLogger(IndexAction.class);
	
	private List<HelloObj> map;
	
	public String perform(HttpServletRequest request,
			HttpServletResponse response) {
		DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.ORA);
		map = dao.getContent().getData();
 		request.getSession().setAttribute("test", map);
 		log.info("index action");
 		return "/Hello.jsp";
	}
}
