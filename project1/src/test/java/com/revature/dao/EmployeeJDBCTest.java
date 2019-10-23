package com.revature.dao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.revature.pojos.Employee;
import com.revature.pojos.Message;
import com.revature.util.ConnectionFactory;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeJDBCTest {
	
private EmployeeJDBC employeeJDBC = new EmployeeJDBC();
	
	Employee employee;
	
	@Mock
	private Connection conn;
	
	@Spy
	private PreparedStatement getStmt = ConnectionFactory.getConnection().prepareStatement("select * from employee where email = ?");
	
	@Spy
	private PreparedStatement getStmtExe = ConnectionFactory.getConnection().prepareStatement("select * from employee where email = ?");

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
	public final void testSetConn() {
		fail("Not yet implemented");
	}

	@Test
	public final void testGetEmployee() {
		
		try {
			when(conn.prepareStatement("select * from employee where email = ?")).thenReturn(getStmt);
			employeeJDBC.setConn(conn);
			
			employeeJDBC.getEmployee("depthead@email.com");
			Mockito.verify(getStmt).executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public final void testGetEmployeeExe() {
		
		try {
			when(conn.prepareStatement("select * from employee where email = ?")).thenReturn(getStmtExe);
			employeeJDBC.setConn(conn);
			
			employeeJDBC.getEmployee("notfound");
			Mockito.verify(getStmtExe).executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public final void testCreateEmployee() {
		fail("Not yet implemented");
	}

	@Test
	public final void testGetAllEmployees() {
		fail("Not yet implemented");
	}

	@Test
	public final void testUpdateEmployee() {
		fail("Not yet implemented");
	}

	@Test
	public final void testDeleteEmployee() {
		fail("Not yet implemented");
	}
	
	public EmployeeJDBCTest() throws SQLException {
		super();
	}

}
