package com.dkoropchenko.hello.model.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.dkoropchenko.hello.model.dao.DAOFactory;
import com.dkoropchenko.hello.model.obj.HelloObj;

public class EditAction implements Action {

	static Logger log = Logger.getLogger(EditAction.class);
	
	@SuppressWarnings("unchecked")
	public String perform(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		List<HelloObj> map = (List<HelloObj>) session.getAttribute("test");
		String content = request.getParameter("content");
		int id = Integer.valueOf(request.getParameter("id"));
		DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.ORA);
		
		if (content != null) {
			map = dao.getContent().editName(id, content);
		}
 		request.getSession().setAttribute("test", map);
 		log.info("edit action");
 		return "/Hello.jsp";
	}

}
