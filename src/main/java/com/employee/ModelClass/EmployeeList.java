package com.employee.ModelClass;

import java.util.List;

public class EmployeeList {
	
	private List<Employee> employees;

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public EmployeeList(List<Employee> employees) {
		this.employees = employees;
	}

	public EmployeeList() {

	}

}
