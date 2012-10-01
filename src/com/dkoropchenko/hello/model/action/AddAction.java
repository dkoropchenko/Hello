package com.dkoropchenko.hello.model.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.dkoropchenko.hello.model.dao.DAOFactory;

public class AddAction implements Action {

	static Logger log = Logger.getLogger(AddAction.class);
	
	@SuppressWarnings("unchecked")
	public String perform(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		Map<String, String> map = (Map<String, String>) session.getAttribute("test");
		String content = request.getParameter("content");
		DAOFactory dao = DAOFactory.getDAOFactory(DAOFactory.ORA);
		
		if (content != null) {
			map = dao.getContent().addNew(generateId(map), content);
		}
		
 		request.getSession().setAttribute("test", map);
 		log.info("add action");
 		return "/Hello.jsp";
	}
	
	@SuppressWarnings("rawtypes")
	private String generateId(Map map) {
		String id = "test" + map.size();
		for (int i = map.size() + 1;;i++)
		if (map.containsKey(id)) {
			id = "test" + i;
		}
		else {
			return id;
		}
	}

}
