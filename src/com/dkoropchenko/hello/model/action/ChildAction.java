package com.dkoropchenko.hello.model.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.dkoropchenko.hello.model.dao.DAOFactory;
import com.dkoropchenko.hello.model.obj.HelloObj;

public class ChildAction implements Action {

private static Logger log = Logger.getLogger(IndexAction.class);
	
	private List<HelloObj> map;
	
	public String perform(HttpServletRequest request,
			HttpServletResponse response) {
		DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.ORA);
		String tmp = request.getParameter("parent_id");
		if (tmp != null) {
			int id = Integer.valueOf(tmp);
			map = dao.getContent().getChildData(id);
 			request.getSession().setAttribute(OBJECTS, map);
 			request.getSession().setAttribute(PARENT_LINK, dao.getContent().getParentLink(id));
		}
 		log.info("index action");
 		return "/Hello.jsp";
	}
}
