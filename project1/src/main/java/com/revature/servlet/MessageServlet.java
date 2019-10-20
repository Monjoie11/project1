package com.revature.servlet;

import static com.revature.util.LoggerUtil.trace;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.pojos.Employee;
import com.revature.pojos.Message;
import com.revature.pojos.Reimbursement;
import com.revature.service.MessageImpl;
import com.revature.service.ReimbursementImpl;

public class MessageServlet extends HttpServlet {

	Message message = new Message();
	MessageImpl messageImpl = new MessageImpl();

	public MessageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Employee employee = (Employee) request.getSession().getAttribute("employee");

		message.setMessageId(0);
		message.setOriginEmail(employee.getEmail());
		message.setTargetEmail(request.getParameter("we neeed to name this field"));
		message.setStatus(Message.Status.valueOf(request.getParameter("we neeed to name this field")));
		message.setContent(request.getParameter("we neeed to name this field"));
		message.setDateCreated(LocalDate.parse(request.getParameter("we neeed to name this field")));

		trace("doPost ReimbursementServlet.java");
		
		messageImpl.addMessageSent(message);


	}

}
