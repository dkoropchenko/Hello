package com.dkoropchenko.hello.model.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.dkoropchenko.hello.model.dao.DAOFactory;

public class EditAction implements Action {

	static Logger log = Logger.getLogger(EditAction.class);
	
	@SuppressWarnings("unchecked")
	public String perform(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		Map<String, String> map = (Map<String, String>) session.getAttribute("test");
		String content = request.getParameter("content");
		String id = request.getParameter("id");
		DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.ORA);
		
		if (id != null && content != null) {
			map = dao.getContent().edit(id, content);
		}
 		request.getSession().setAttribute("test", map);
 		log.info("edit action");
 		return "/Hello.jsp";
	}

}
