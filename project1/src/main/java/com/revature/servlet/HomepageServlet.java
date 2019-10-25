package com.revature.servlet;

import java.io.IOException;
import static com.revature.util.LoggerUtil.*;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojos.Employee;
import com.revature.pojos.Message;
import com.revature.pojos.Reimbursement;
import com.revature.service.MessageImpl;
import com.revature.service.ReimbursementImpl;

public class HomepageServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MessageImpl messageImpl = new MessageImpl();
	ReimbursementImpl reimbursementImpl = new ReimbursementImpl();

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session;
		session = request.getSession(false);
		Employee employee = (Employee) session.getAttribute("employee");
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

	}

}

/*
 * ObjectMapper om = new ObjectMapper();
 * 
 * Employee employee = (Employee) request.getSession().getAttribute("employee");
 * 
 * List<Message> messageList =
 * messageImpl.getMessagesByUser(employee.getEmail());
 * 
 * List<Reimbursement> reimbursementList =
 * reimbursementImpl.getReimbursmentList(employee);
 */