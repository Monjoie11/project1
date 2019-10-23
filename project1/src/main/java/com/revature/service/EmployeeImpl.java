package com.revature.service;

import static com.revature.util.LoggerUtil.trace;
import com.revature.dao.EmployeeJDBC;
import com.revature.pojos.Employee;

public class EmployeeImpl implements EmployeeService {

	EmployeeJDBC employeeJDBC = new EmployeeJDBC();

	@Override
	public Employee loginEmployee(String email, String password) {
		trace("Attempted login with credentials: Username - " + email + " Password - " + password);

		Employee employee = employeeJDBC.getEmployee(email);

		if (((employee.getEmail() != null) && (employee.getPassword() != null))
				&& (employee.getPassword().equals(password))) {
			return employee;
		}
		trace("login emp returned null");
		return null;
	}

}
