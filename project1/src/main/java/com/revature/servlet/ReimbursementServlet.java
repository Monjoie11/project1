package com.revature.servlet;

import static com.revature.util.LoggerUtil.trace;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.pojos.Reimbursement;

public class ReimbursementServlet extends HttpServlet{

	Reimbursement reimbursement = new Reimbursement();
	
	public ReimbursementServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		reimbursement.setReimbursementId(0);
		reimbursement.setStartDate(LocalDate.parse(request.getParameter("date")));
		reimbursement.setStartTime(LocalTime.parse(request.getParameter("time")));
		reimbursement.setLocation(request.getParameter("location"));
		reimbursement.setDescription(request.getParameter("description"));
		reimbursement.setCost(Double.parseDouble(request.getParameter("cost")));
		reimbursement.setGradingFormat(request.getParameter("grading-format"));
		reimbursement.setEventType(request.getParameter("event-type"));
		reimbursement.setJustification(request.getParameter("work-related-justification"));
		reimbursement.setDateSubmitted(LocalDate.parse(request.getParameter("date")));
		reimbursement.setEmail(request.getParameter("email"));
		reimbursement.setTimeMissed(Integer.parseInt(request.getParameter("time-missed")));


		trace("doPost ReimbursementServlet.java");
		
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPut(req, resp);
	}
	
	
	
}
