package com.dkoropchenko.hello.ctrl.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.dkoropchenko.hello.ctrl.dao.DAOFactory;
import com.dkoropchenko.hello.model.HelloObj;

public class DeleteAction implements Action {

	static Logger log = Logger.getLogger(DeleteAction.class);
	
	@SuppressWarnings("unchecked")
	public String perform(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		List<HelloObj> map = (List<HelloObj>) session.getAttribute(OBJECTS);
		int id = Integer.valueOf(request.getParameter("id"));
		int parent_id = Integer.valueOf(request.getParameter("parent"));
		DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.ORA);
		
		map = dao.getContent().delete(id);
 		
		request.getSession().setAttribute(OBJECTS, map);
		request.getSession().setAttribute(PARENT_LINK, dao.getContent().getParentLink(parent_id));
 		log.info("delete action");
 		return "/Hello.jsp";
	}

}
