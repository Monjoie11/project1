package com.revature.service;

import java.time.LocalDate;
import java.util.List;
import static com.revature.util.LoggerUtil.*;

import com.revature.dao.MessageJDBC;
import com.revature.dao.ReimbursementJDBC;
import com.revature.pojos.Employee;
import com.revature.pojos.Message;
import com.revature.pojos.Reimbursement;
import com.revature.pojos.Message.Status;

public class ReimbursementImpl implements ReimbursementService {
	ReimbursementJDBC reimbursementJDBC = new ReimbursementJDBC();
	MessageJDBC messageJDBC = new MessageJDBC();
	Message message = new Message();
	
	
	private final String APPROVES = "your Reimbursement Request has advanced its approval plaese wath your dashboard for updates";
	
	private final String DENIES = "your Reimbursement request has been denied";
	
	private final String MOREINFO = "More information is required to complete your reimbursement request.";
	
	private final String MODIFIED = "your reimbursement request has been adjusted by the benefits coordinator.";
	
	private final String ACCEPTED = "Your reimbursement request was accepted. Expect payment two weeks following"
			+ " the first of the month a week after the end of the quarter in which this message was received";

	@Override
	public boolean addReimbursementRequest(Reimbursement reimbursement) {

		trace("reimbusementimpl add");
		reimbursementJDBC.createReimbursement(reimbursement);

		return true;
	}

	@Override
	public List<Reimbursement> getReimbursmentList(Employee employee) {
		trace("reimbusementimpl get list");
		if (employee.getRole().equals(Employee.Role.EMPLOYEE)) {
			return reimbursementJDBC.getEmployeeReimbursement(employee.getEmail());
		} else {
			return reimbursementJDBC.getAllReimbursements();
		}

	}

	@Override
	public boolean updateReimbursementStatus(Reimbursement reimbursement, Employee employee, String inputAction) {

		Employee.Role role = employee.getRole();
		
	/*	private int messageId;
		
		private String originEmail;
		
		private String targetEmail;
		
		private Status status;
		
		private String content;
		
		private LocalDate dateCreated;*/
		
		switch (role) {
		case SUPERVISOR:

			switch (inputAction) {
			case "Approved":
				reimbursementJDBC.updateReimbursementStatus(reimbursement.getReimbursementId(), Reimbursement.Status.NEEDHEADAPPROV);
				message = new Message(0, employee.getEmail(), reimbursement.getEmail(), Message.Status.SENT, APPROVES, reimbursement.getDateSubmitted());
				messageJDBC.createMessage(message);
				break;

			case "Rejected":
				reimbursementJDBC.updateReimbursementStatus(reimbursement.getReimbursementId(), Reimbursement.Status.DENIED);
				message = new Message(0, employee.getEmail(), reimbursement.getEmail(), Message.Status.SENT, DENIES, reimbursement.getDateSubmitted());
				messageJDBC.createMessage(message);
				break;

			case "MoreInfo":
				reimbursementJDBC.updateReimbursementStatus(reimbursement.getReimbursementId(), Reimbursement.Status.MOREINFO);
				message = new Message(0, employee.getEmail(), reimbursement.getEmail(), Message.Status.SENT, MOREINFO, reimbursement.getDateSubmitted());
				messageJDBC.createMessage(message);
			}

			break;

		case DEPTHEAD:

			switch (inputAction) {
			case "Approved":
				reimbursementJDBC.updateReimbursementStatus(reimbursement.getReimbursementId(), Reimbursement.Status.NEEDBENCOAPROV);
				message = new Message(0, employee.getEmail(), reimbursement.getEmail(), Message.Status.SENT, APPROVES, reimbursement.getDateSubmitted());
				messageJDBC.createMessage(message);
				break;

			case "Rejected":
				reimbursementJDBC.updateReimbursementStatus(reimbursement.getReimbursementId(), Reimbursement.Status.DENIED);
				message = new Message(0, employee.getEmail(), reimbursement.getEmail(), Message.Status.SENT, DENIES, reimbursement.getDateSubmitted());
				messageJDBC.createMessage(message);
				break;

			case "MoreInfo":
				reimbursementJDBC.updateReimbursementStatus(reimbursement.getReimbursementId(), Reimbursement.Status.MOREINFO);
				message = new Message(0, employee.getEmail(), reimbursement.getEmail(), Message.Status.SENT, MOREINFO, reimbursement.getDateSubmitted());
				messageJDBC.createMessage(message);
			}

			break;

		case BENCO:

			switch (inputAction) {
			case "Approved":
				reimbursementJDBC.updateReimbursementStatus(reimbursement.getReimbursementId(), Reimbursement.Status.ACCEPTED);
				message = new Message(0, employee.getEmail(), reimbursement.getEmail(), Message.Status.SENT, ACCEPTED, reimbursement.getDateSubmitted());
				messageJDBC.createMessage(message);
				break;

			case "Rejected":
				reimbursementJDBC.updateReimbursementStatus(reimbursement.getReimbursementId(), Reimbursement.Status.DENIED);
				message = new Message(0, employee.getEmail(), reimbursement.getEmail(), Message.Status.SENT, DENIES, reimbursement.getDateSubmitted());
				messageJDBC.createMessage(message);
				break;

			case "MoreInfo":
				reimbursementJDBC.updateReimbursementStatus(reimbursement.getReimbursementId(), Reimbursement.Status.MOREINFO);
				message = new Message(0, employee.getEmail(), reimbursement.getEmail(), Message.Status.SENT, MOREINFO, reimbursement.getDateSubmitted());
				messageJDBC.createMessage(message);
				break;

			case "Modified":// write chain for changing total amount plus informing employee of the change
				message = new Message(0, employee.getEmail(), reimbursement.getEmail(), Message.Status.SENT, MODIFIED, reimbursement.getDateSubmitted());
				messageJDBC.createMessage(message);
				break;
			}

			
		}

		return false;
	}

}
