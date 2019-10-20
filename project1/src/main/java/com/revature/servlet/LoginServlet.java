package com.revature.servlet;

import java.io.IOException;
//import java.net.http.HttpResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.pojos.Employee;
import com.revature.service.EmployeeImpl;

import static com.revature.util.LoggerUtil.*;

public class LoginServlet extends HttpServlet {
	Employee employee = new Employee();
	EmployeeImpl empImpl = new EmployeeImpl();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletRequest response) throws ServletException, IOException {
		trace("login doGet");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		String employeeEmail = request.getParameter("employee-email");
		String employeePassword = request.getParameter("employee-password");

		trace("doPost LoginServlet.java " + employeeEmail + " " + employeePassword);
		
		employee = empImpl.loginEmployee(employeeEmail, employeePassword);
		if (employee != null) {
			HttpSession session;
			session = request.getSession(true);
			//request.getsession vs seession.setattribute
			request.getSession().setAttribute("employee", employee);
			response.sendRedirect("home.component.html");
		} else {
			response.getWriter().write("Sorry, but you were not able to login correctly :(");
		}
	}

}



//code to direct flow based on employee role
/*
 * if (employee.getUsername().equals("user")) { response.sendRedirect("man"); }
 * else { response.sendRedirect("emp"); }
 */