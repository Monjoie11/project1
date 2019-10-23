package com.revature.servlet;

import java.io.IOException;
import static com.revature.util.LoggerUtil.*;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojos.Employee;
import com.revature.pojos.Message;
import com.revature.pojos.Reimbursement;
import com.revature.service.MessageImpl;
import com.revature.service.ReimbursementImpl;

public class HomepageServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MessageImpl messageImpl = new MessageImpl();
	ReimbursementImpl reimbursementImpl = new ReimbursementImpl();
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
	}
	
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	
		
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
		
		 
	}
	

}
