package com.revature.dao;

import static com.revature.util.LoggerUtil.trace;
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
	
	private static Connection conn = ConnectionFactory.getConnection();
	
	public void setConn(Connection conn) {
		EmployeeJDBC.conn = conn;
	}

	@Override
	public Employee getEmployee(String email) {
		String sql = "select * from employee where email = ?";
		
		
		
		Employee employee = new Employee();
		
		try {
			PreparedStatement stmt;
			stmt = EmployeeJDBC.conn.prepareStatement(sql);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				employee.setEmail(rs.getString(1));
				employee.setFirstName(rs.getString(2));
				employee.setLastName(rs.getString(3));
				employee.setPassword(rs.getString(4));
				employee.setDepartment(Employee.Department.valueOf(rs.getString(5)));
				employee.setPhone(rs.getString(6));
				employee.setRole(Employee.Role.valueOf(rs.getString(7)));
			}
			
		} catch (SQLException e) {
			trace("emp jdbc catch get employee");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		trace("get emp jdbc" + employee.toString());
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
