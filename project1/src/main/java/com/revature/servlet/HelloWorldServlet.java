package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorldServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		System.out.println("Hello World!!!!!!!!");
		//throw new ServletException();
		PrintWriter pw = resp.getWriter();
		String position = req.getParameter("position");
		String name = req.getParameter("name");
		pw.write("<h1>Hello " + position + " " + name + "</h1>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		System.out.println("Hello Post!!!!!");
		resp.getWriter().write("THis is a post method trust me");
	}

}
