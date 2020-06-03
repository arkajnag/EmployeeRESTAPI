package com.employee.DataSource;

import java.util.ArrayList;
import java.util.List;
import com.employee.ModelClass.Employee;

public class EmployeeDataSource {
	
	private static List<Employee> employeeDataSource = new ArrayList<Employee>();

	public static List<Employee> getEmployeeDataSource() {
		return employeeDataSource;
	}

	public static void setEmployeeDataSource(List<Employee> employeeDataSource) {
		EmployeeDataSource.employeeDataSource = employeeDataSource;
	}

}
