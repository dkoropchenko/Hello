package com.dkoropchenko.hello.model.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.dkoropchenko.hello.model.dao.DAOFactory;
import com.dkoropchenko.hello.model.obj.HelloObj;

public class DeleteAction implements Action {

	static Logger log = Logger.getLogger(DeleteAction.class);
	
	@SuppressWarnings("unchecked")
	public String perform(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		List<HelloObj> map = (List<HelloObj>) session.getAttribute("test");
		int id = Integer.valueOf(request.getParameter("id"));
		DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.ORA);
		
		map = dao.getContent().delete(id);
 		
		request.getSession().setAttribute("test", map);
 		log.info("delete action");
 		return "/Hello.jsp";
	}

}
