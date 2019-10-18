package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectServlet extends HttpServlet {
	
	/*
	 * Redirect
	 * 	- give a url to a new resource
	 * 	- response will tell the client to send another request for this resource
	 * 	- Pro: client is aware of new url
	 * 	- Con: slower, lose request scope
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Inside doGet of RedirectServlet");
		resp.sendRedirect("hello");
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Inside doPost of RedirectServlet");
		resp.sendRedirect("hello");
	}

}
