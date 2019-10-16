package com.revature.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForwardServlet extends HttpServlet{
	
	/*
	 * Forward
	 * 	- pass the req and resp objects to a new resource
	 * 	- the new resource responds back to the client
	 * 	- Pros: faster(only 1 request and response) & request Scope is maintained
	 * 	- Cons: client is unaware of what is happening (could be a pro)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		System.out.println("Inside doGet of ForwardServlet");
		RequestDispatcher rd = req.getRequestDispatcher("hello");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		System.out.println("Inside doPost of ForwardServlet");
		RequestDispatcher rd = req.getRequestDispatcher("hello");
		rd.forward(req, resp);
	}

}
