package com.revature.dao;

import java.util.List;

import com.revature.pojos.Employee;
;

public interface EmployeeDao {
	
	
	public Employee getEmployee(String email);
	
	public void createEmployee(Employee employee);
	
	public List<Employee> getAllEmployees();
	
	public void updateEmployee(Employee employee);
	
	public void deleteEmployee(Employee employee);

}
