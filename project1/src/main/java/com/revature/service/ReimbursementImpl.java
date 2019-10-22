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
	public boolean updateReimbursementStatus(int reimbursmentId, 
			Reimbursement.Status newStatus, Employee.Role role, String inputAction) {
		// TODO Auto-generated method stub
		return false;
	}

}
