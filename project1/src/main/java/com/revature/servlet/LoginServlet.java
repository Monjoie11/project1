package com.revature.servlet;

import java.io.IOException;
//import java.net.http.HttpResponse;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.pojos.Employee;
import com.revature.service.EmployeeImpl;
import com.revature.service.MessageImpl;

import static com.revature.util.LoggerUtil.*;

public class LoginServlet extends HttpServlet {
	Employee employee = new Employee();
	EmployeeImpl empImpl = new EmployeeImpl();
	MessageImpl messageImpl = new MessageImpl();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String employeeEmail = request.getParameter("employee-email");
		String employeePassword = request.getParameter("employee-password");

		trace("doPost LoginServlet.java " + employeeEmail + " " + employeePassword);

		employee = empImpl.loginEmployee(employeeEmail, employeePassword);
		if (employee != null) {
			HttpSession session;
			session = request.getSession(true);
			trace("logged in now redirecting");
			// request.getsession vs seession.setattribute
			session.setAttribute("employee", employee); // html name tag
			Employee.Role role = employee.getRole();
			switch (role) {

			case EMPLOYEE:
				response.sendRedirect("home.employee.html");
				trace("emp login serv switch");
				break;
			case DEPTHEAD:
				response.sendRedirect("home.depart.html");
				trace("dept login serv switch");
				break;
			case SUPERVISOR:
				response.sendRedirect("home.direct.html");
				trace("super login serv switch");
				break;
			case BENCO:
				response.sendRedirect("home.benco.html");
				trace("benco login serv switch");
				break;
			default:
				session.invalidate();
				trace("default login serv switch");
				response.sendRedirect("login.component.html");
				break;

			}

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