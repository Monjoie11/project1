package com.revature.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
//import java.net.http.HttpResponse;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.MessageJDBC;
import com.revature.dao.ReimbursementJDBC;
import com.revature.pojos.Employee;
import com.revature.pojos.Message;
import com.revature.pojos.Reimbursement;
import com.revature.service.EmployeeImpl;
import com.revature.service.MessageImpl;

import static com.revature.util.LoggerUtil.*;

public class LoginServlet extends HttpServlet {
	Employee employee = new Employee();
	EmployeeImpl empImpl = new EmployeeImpl();
	MessageImpl messageImpl = new MessageImpl();
	ReimbursementJDBC reimbursementJDBC = new ReimbursementJDBC();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}
	
	

	@Override
	public void init() throws ServletException {
		
		List<Reimbursement> reimbusList = new ArrayList<Reimbursement>();
		
		reimbusList = reimbursementJDBC.getAllReimbursements();
		
	
		
		Message message = new Message();
		
		MessageJDBC messageJDBC = new MessageJDBC();
		
		 
		
		LocalDate today = LocalDate.now();
		
		//sup 7 days
		//head 3 days
		//benco 24 hours
		
		for(Reimbursement r: reimbusList) {
			
			final String BENSLOW = "The benefits has not taken action on " + String.valueOf(r.getReimbursementId()) + ". The system"
					+ "is informing the benco, applying employee, and benfits supervisor of the lapse.";
			
			switch(r.getStatus()) {
			case NEEDSUPAPPROV:
				if(today.isAfter(r.getDateSubmitted().plusDays(7))){
					reimbursementJDBC.updateReimbursementStatus(r.getReimbursementId(), Reimbursement.Status.NEEDHEADAPPROV);
				}
				break;
			case NEEDHEADAPPROV:
				if(today.isAfter(r.getDateSubmitted().plusDays(3))){
					reimbursementJDBC.updateReimbursementStatus(r.getReimbursementId(), Reimbursement.Status.NEEDBENCOAPROV);
				}
				break;
			case NEEDBENCOAPROV:
				if(today.isAfter(r.getDateSubmitted().plusDays(1))) {
				message = new Message(0, "DONOTREPLY@email.com", "bensupervisor@email.com", Message.Status.SENT, BENSLOW, r.getDateSubmitted());
				messageJDBC.createMessage(message);	
				message = new Message(0, "DONOTREPLY@email.com", r.getEmail(), Message.Status.SENT, BENSLOW, r.getDateSubmitted());
				messageJDBC.createMessage(message);	
				}
				break;
			default:
				break;
			}
		
		}
		
		
		
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
