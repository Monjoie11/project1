package com.revature.service;

import com.revature.dao.ReimbursementJDBC;
import com.revature.pojos.Reimbursement;

public class ReimbursementImpl implements ReimbursementService{

	@Override
	public boolean addReimbursementRequest(Reimbursement reimbursement) {
		ReimbursementJDBC reimbursementJDBC = new ReimbursementJDBC();
		
		reimbursementJDBC.createReimbursement(reimbursement);
		
		return true;
	}

}
