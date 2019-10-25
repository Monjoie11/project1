package com.revature.servlet;

import static com.revature.util.LoggerUtil.trace;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.ReimbursementJDBC;
import com.revature.pojos.Employee;
import com.revature.pojos.Reimbursement;
import com.revature.service.ReimbursementImpl;

/**
 * Servlet implementation class LogOut
 */
public class LogOut extends HttpServlet {
	
	ReimbursementJDBC reimbursementJDBC = new ReimbursementJDBC();
	ReimbursementImpl reimbursementImpl = new ReimbursementImpl();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogOut() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session;
		session = request.getSession(false);
		session.invalidate();
		response.sendRedirect("login.component.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	int reimbursementId = Integer.parseInt(request.getParameter("reimbursementId"));
		
		Reimbursement reimbursement = reimbursementJDBC.getReimbursement(reimbursementId);
		
		
		
		HttpSession session;
		session = request.getSession(false);
		Employee employee = (Employee) session.getAttribute("employee");
		String inputAction = request.getParameter("action");
		
		String newValue = request.getParameter("newAmount");
		int totalAmount = 0;
				
		if(newValue != null) {
		 totalAmount = Integer.parseInt(newValue);
		} 
			
		
		trace(request.getParameter("newAmount") + request.getParameter("action") + "reimb doPut");
		
		reimbursementImpl.updateReimbursementStatus(reimbursement, employee, inputAction, totalAmount);
		
		response.sendRedirect("home");
	}

}
