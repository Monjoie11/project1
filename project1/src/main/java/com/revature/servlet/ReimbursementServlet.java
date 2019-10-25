package com.revature.servlet;

import static com.revature.util.LoggerUtil.trace;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
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
import com.revature.service.ReimbursementImpl;

public class ReimbursementServlet extends HttpServlet{

	Reimbursement reimbursement = new Reimbursement();
	ReimbursementImpl reimbursementImpl = new ReimbursementImpl();
	
	
	public ReimbursementServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Employee employee = (Employee) request.getSession().getAttribute("employee");
		
		reimbursement.setReimbursementId(0);
		
		reimbursement.setStartDate(LocalDate.parse(request.getParameter("date")));
		
		reimbursement.setStartTime(LocalTime.parse(request.getParameter("time")));
		
		reimbursement.setLocation(request.getParameter("location"));
		reimbursement.setDescription(request.getParameter("description"));
		reimbursement.setCost(Double.parseDouble(request.getParameter("cost")));
		reimbursement.setGradingFormat(request.getParameter("grading-format"));
		reimbursement.setEventType(request.getParameter("event-type"));
		reimbursement.setJustification(request.getParameter("work-related-justification"));
		reimbursement.setDateSubmitted(LocalDate.now());
		reimbursement.setEmail(employee.getEmail());
		reimbursement.setTimeMissed(Integer.parseInt(request.getParameter("time-missed")));
        reimbursement.setTotalAmount(reimbursementImpl.makeTotalAmount(reimbursement.getCost(), reimbursement.getEventType()));

		trace("doPost ReimbursementServlet.java");

		if(reimbursementImpl.checkAnnualTotal(reimbursement.getEmail())) {
			reimbursement.setStatus(Reimbursement.Status.NEEDSUPAPPROV);
		} else {
			reimbursement.setStatus(Reimbursement.Status.DENIED);
		}
		
		reimbursementImpl.addReimbursementRequest(reimbursement);
		
		response.sendRedirect("home");
		
		
		
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		trace("doGet reimbursementServlet");
		ObjectMapper om = new ObjectMapper();
		
		Employee employee = (Employee) request.getSession().getAttribute("employee");
		
		
		List<Reimbursement> reimbursementList = reimbursementImpl.getReimbursmentList(employee);
		trace(reimbursementList.toString());
		response.setContentType("text/plain");
		response.getWriter().write(om.writeValueAsString(reimbursementList));
	}
	
	
	
}
