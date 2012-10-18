package com.dkoropchenko.hello.ctrl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.dkoropchenko.hello.ctrl.action.Action;


public class HelloWorldServlet extends HttpServlet {

	/**
	 * Log4j logger
	 */
	static Logger log = Logger.getLogger(HelloWorldServlet.class);
	
	/*static {
		PropertyConfigurator.configure("/log4j.properties");
	}*/
	
	protected ActionFactory factory = new ActionFactory();
	
	private static final long serialVersionUID = 1L;

	public HelloWorldServlet() {
		super();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String act = getActionName(request);
		log.info("action: " + act);
		try {
			Action action = factory.create(act);
			String url = action.perform(request, response);
			log.info("service");
			if (url != null)
				getServletContext().getRequestDispatcher(url).forward(request, response);
		}
		catch (Exception e) {
			request.setAttribute("error", e);
			getServletContext().getRequestDispatcher("/Error.jsp").forward(request, response);
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	private String getActionName(HttpServletRequest request) {
		String path = request.getParameter("action");
		log.info("servlet path: " + path);
		if (path != null && !("".equals(path))) {
			return path;
		}
		else
			return "hello";
	}
}
