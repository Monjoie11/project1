package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static com.revature.util.LoggerUtil.*;

public class LifeCycleServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		trace("LifeCycle doGet");
		super.doGet(req, resp);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		trace("LifeCycle service");
		super.service(req, resp);
	}

	@Override
	public void destroy() {
		trace("This is KBEN signing off. Goodnight, radioland");
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		trace("LifeCycle doGet");
		super.init();
	}

}
