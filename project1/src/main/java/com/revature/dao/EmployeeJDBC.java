package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojos.Employee;
import com.revature.util.ConnectionFactory;

public class EmployeeJDBC implements EmployeeDao {
	
	List<Employee> employeeRepository = new ArrayList<>();
	
	private Connection conn = ConnectionFactory.getConnection();
	
	public void setConn(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Employee getEmployee(String email) {
		String sql = "select * from employee where email = ?";
		
		PreparedStatement stmt;
		
		Employee employee = new Employee();
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				employee.setEmail(rs.getString(1));
				employee.setFirstName(rs.getString(2));
				employee.setLastName(rs.getString(3));
				employee.setDepartment(Employee.Department.valueOf(rs.getString(4)));
				employee.setPhone(rs.getString(5));
				employee.setRole(Employee.Role.valueOf(rs.getString(6)));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return employee;
		
	}
	

	@Override
	public void createEmployee(Employee employee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteEmployee(Employee employee) {
		// TODO Auto-generated method stub
		
	}

}
