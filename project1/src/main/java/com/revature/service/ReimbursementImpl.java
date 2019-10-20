package com.revature.service;

import com.revature.dao.ReimbursementJDBC;
import com.revature.pojos.Reimbursement;

public class ReimbursementImpl implements ReimbursementService{
	ReimbursementJDBC reimbursementJDBC = new ReimbursementJDBC();
	
	@Override
	public boolean addReimbursementRequest(Reimbursement reimbursement) {
		
		
		reimbursementJDBC.createReimbursement(reimbursement);
		
		return true;
	}

}
