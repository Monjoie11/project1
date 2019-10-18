package com.revature.service;


import com.revature.dao.EmployeeJDBC;
import com.revature.pojos.Employee;

public class EmployeeImpl implements EmployeeService {
	
	EmployeeJDBC employeeJDBC = new EmployeeJDBC();

	@Override
	public Employee loginEmployee(String email, String password) {
		System.out.println("Attempted login with credentials: Username - " + email + " Password - " + password);
		
		Employee employee = employeeJDBC.getEmployee(email);
		
		if ((employee != null) && (employee.getPassword().equals(password))) {
			return employee;
		}
		
		return null;
	}

}
