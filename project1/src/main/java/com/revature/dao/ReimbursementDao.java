package com.revature.dao;

import java.util.List;

import com.revature.pojos.Reimbursement;



public interface ReimbursementDao {
	
	public Reimbursement getReimbursement(int reimbursementId);
	
	public boolean createReimbursement(Reimbursement reimbursement);
	
	public List<Reimbursement> getAllReimbursements();
	
	public void updateReimbursement(Reimbursement reimbursement);
	
	public void deleteReimbursement(Reimbursement reimbursement);

}
