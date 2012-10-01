package com.dkoropchenko.hello.model.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.dkoropchenko.hello.model.dao.DAOFactory;

public class DeleteAction implements Action {

	static Logger log = Logger.getLogger(DeleteAction.class);
	
	@SuppressWarnings("unchecked")
	public String perform(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		Map<String, String> map = (Map<String, String>) session.getAttribute("test");
		String id = request.getParameter("id");
		DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.ORA);
		
		if (id != null) {
			map = dao.getContent().delete(id);
		}
 		request.getSession().setAttribute("test", map);
 		log.info("delete action");
 		return "/Hello.jsp";
	}

}
