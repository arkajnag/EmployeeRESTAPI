package com.employee.ModelClass;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Employee {
	
	private Integer employeeID;

	private String employeeName;
	private String employeeDept;
	private Integer employeeAge;
	private Address employeeAddress;
	
	public Integer getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeDept() {
		return employeeDept;
	}
	public void setEmployeeDept(String employeeDept) {
		this.employeeDept = employeeDept;
	}
	public Integer getEmployeeAge() {
		return employeeAge;
	}
	public void setEmployeeAge(Integer employeeAge) {
		this.employeeAge = employeeAge;
	}
	public Address getEmployeeAddress() {
		return employeeAddress;
	}
	public void setEmployeeAddress(Address employeeAddress) {
		this.employeeAddress = employeeAddress;
	}
	public Employee() {
		
	}
	public Employee(Integer employeeID) {
		this.employeeID = employeeID;
	}
	public Employee(Integer employeeID, String employeeName, String employeeDept, Integer employeeAge,
			Address employeeAddress) {
		this.employeeID = employeeID;
		this.employeeName = employeeName;
		this.employeeDept = employeeDept;
		this.employeeAge = employeeAge;
		this.employeeAddress = employeeAddress;
	}
}
