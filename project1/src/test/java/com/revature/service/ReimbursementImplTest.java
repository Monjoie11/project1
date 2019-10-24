package com.revature.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.EmployeeJDBC;
import com.revature.dao.ReimbursementJDBC;

public class ReimbursementImplTest {
	
	ReimbursementImpl reimbursementImpl = new ReimbursementImpl();
	
	ReimbursementJDBC reimbursementJDBC = new ReimbursementJDBC();
	
	EmployeeJDBC employeeJDBC = new EmployeeJDBC();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testUpdateReimbursementStatus() {
		assertTrue(reimbursementImpl.updateReimbursementStatus(reimbursementJDBC.getReimbursement(2), employeeJDBC.getEmployee("email@email.com"), "Approved"));
		assertFalse(!reimbursementImpl.updateReimbursementStatus(reimbursementJDBC.getReimbursement(2), employeeJDBC.getEmployee("email@email.com"), "Approved"));
	}

	@SuppressWarnings("deprecation")
	@Test
	public final void testMakeTotalAmount() {
		double total = reimbursementImpl.makeTotalAmount(1000, "Other");
		double newTotal = reimbursementImpl.makeTotalAmount(1000, "University Course");
		assertEquals(total, 300.00, .1);
		assertEquals(newTotal, 800.00, .1);
	}

	@Test
	public final void testCheckAnnualTotal() {
		assertFalse(reimbursementImpl.checkAnnualTotal("email@email.com"));
		assertFalse(reimbursementImpl.checkAnnualTotal("depthead@email.com"));
	}

}
