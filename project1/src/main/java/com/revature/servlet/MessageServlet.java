package com.revature.servlet;

import static com.revature.util.LoggerUtil.trace;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojos.Employee;
import com.revature.pojos.Message;
import com.revature.service.MessageImpl;

public class MessageServlet extends HttpServlet {

	Message message = new Message();
	MessageImpl messageImpl = new MessageImpl();
	List messageList = new ArrayList<>();
	
	public MessageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		trace("doGet messageServlet");
		ObjectMapper om = new ObjectMapper();
		
		Employee employee = (Employee) request.getSession().getAttribute("employee");
		
		
		messageList = messageImpl.getMessagesByUser(employee.getEmail());
		trace(messageList.toString());
		response.setContentType("text/plain");
		response.getWriter().write(om.writeValueAsString(messageList));
		
	}
	
	

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Employee employee = (Employee) request.getSession().getAttribute("employee");
		LocalDate msgRecieved = LocalDate.now();
		message.setMessageId(0);
		message.setOriginEmail(employee.getEmail());
		message.setTargetEmail(request.getParameter("sendTo"));
		message.setStatus(Message.Status.SENT);
		message.setContent(request.getParameter("msgContext"));
		message.setDateCreated(msgRecieved);

		trace("doPost ReimbursementServlet.java");
		
		messageImpl.addMessageSent(message);


	}

}
