package com.revature.service;

import java.util.List;

import com.revature.pojos.Employee;
import com.revature.pojos.Reimbursement;

public interface ReimbursementService {

	public boolean addReimbursementRequest(Reimbursement reimbursement);
	
	public List<Reimbursement> getReimbursmentList(Employee employee);
	
	public boolean updateReimbursementStatus(Reimbursement reimbursement, Employee employee, String inputAction);
	
}
