package com.revature.service;

import java.util.List;

import com.revature.dao.ReimbursementJDBC;
import com.revature.pojos.Employee;
import com.revature.pojos.Reimbursement;

public class ReimbursementImpl implements ReimbursementService{
	ReimbursementJDBC reimbursementJDBC = new ReimbursementJDBC();
	
	@Override
	public boolean addReimbursementRequest(Reimbursement reimbursement) {
		
		
		reimbursementJDBC.createReimbursement(reimbursement);
		
		return true;
	}
	

	@Override
	public List<Reimbursement> getReimbursmentList(Employee employee) {
	
		if(employee.getRole().equals(Employee.Role.EMPLOYEE)) {
			return reimbursementJDBC.getEmployeeReimbursement(employee.getEmail());
		} else {
			return reimbursementJDBC.getAllReimbursements();
		}
		
		
	}


	@Override
	public boolean updateReimbursementStatus(int reimbursementId, Employee.Role role, String inputAction) {
		
		switch(role) {
		case SUPERVISOR: 
			
			switch(inputAction) {
			case "Approved": reimbursementJDBC.updateReimbursementStatus(reimbursementId, Reimbursement.Status.NEEDHEADAPPROV);
			break;
			
			case "Rejected": reimbursementJDBC.updateReimbursementStatus(reimbursementId, Reimbursement.Status.DENIED);
			//TODO create logic to send message back to requesting employee informing them of rejection
			break;
			
			case "MoreInfo": reimbursementJDBC.updateReimbursementStatus(reimbursementId, Reimbursement.Status.MOREINFO); 
			//TODO create logic to send message back to requesting employee informing them that more info has been requested, 
			//and which supervisor/benco requested it 
			}
			
			break;
			
		case DEPTHEAD:
			
			switch(inputAction) {
			case "Approved": reimbursementJDBC.updateReimbursementStatus(reimbursementId, Reimbursement.Status.NEEDBENCOAPROV);
			break;
			
			case "Rejected": reimbursementJDBC.updateReimbursementStatus(reimbursementId, Reimbursement.Status.DENIED);
			//TODO create logic to send message back to requesting employee informing them of rejection
			break;
			
			case "MoreInfo": reimbursementJDBC.updateReimbursementStatus(reimbursementId, Reimbursement.Status.MOREINFO); 
			//TODO create logic to send message back to requesting employee informing them that more info has been requested, 
			//and which supervisor/benco requested it 
			}
			
			break;
			
		case BENCO:
			
			switch(inputAction) {
			case "Approved": reimbursementJDBC.updateReimbursementStatus(reimbursementId, Reimbursement.Status.ACCEPTED);
			break;
			
			case "Rejected": reimbursementJDBC.updateReimbursementStatus(reimbursementId, Reimbursement.Status.DENIED);
			//TODO create logic to send message back to requesting employee informing them of rejection
			break;
			
			case "MoreInfo": reimbursementJDBC.updateReimbursementStatus(reimbursementId, Reimbursement.Status.MOREINFO); 
			//TODO create logic to send message back to requesting employee informing them that more info has been requested, 
			//and which supervisor/benco requested it 
			}
			
			break;
		}
		
		
		return false;
	}

}
