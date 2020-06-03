package com.employee.Services;

import java.util.List;
import java.util.stream.Collectors;
import com.employee.DataSource.EmployeeDataSource;
import com.employee.ExceptionHandler.DataNotFoundException;
import com.employee.ExceptionHandler.DataValueException;
import com.employee.ExceptionHandler.DuplicateDataException;
import com.employee.ExceptionHandler.ServerException;
import com.employee.ModelClass.Employee;
import com.employee.ModelClass.EmployeeList;
import com.employee.Utilities.CommonUtils;

public class EmployeeService {

	private List<Employee> employeeList = EmployeeDataSource.getEmployeeDataSource();
	private EmployeeList allEmployeesWrapper = new EmployeeList(employeeList);

	public Object getAllEmployeeDetails() throws DataNotFoundException {
			if (allEmployeesWrapper.getEmployees().size() > 0)
				return allEmployeesWrapper;
			throw new DataNotFoundException("No Employee Records found!!");
	}

	public Employee postNewEmployeeDetails(Employee empObj) throws DuplicateDataException, ServerException {
		Integer empID = empObj.getEmployeeID();
		String empName = empObj.getEmployeeName();
		String empDept = empObj.getEmployeeDept();
		Integer empAge = empObj.getEmployeeAge();
		String empCity = empObj.getEmployeeAddress().getCity();
		String empCountry = empObj.getEmployeeAddress().getCountry();
		if (empID==null||empID==0||empID<0)
			throw new NullPointerException("Employee ID can't be NULL or Blank or less than 0");
		if (empName.trim().equalsIgnoreCase("") || (empName.equals(null)))
			throw new NullPointerException("Employee Name can't be NULL or Blank");
		if (empDept.trim().equalsIgnoreCase("") || (empDept.equals(null)))
			throw new NullPointerException("Employee Department can't be NULL or Blank");
		if (empCity.trim().equalsIgnoreCase("") || (empCity.equals(null)))
			throw new NullPointerException("Employee City can't be NULL or Blank");
		if (empCountry.trim().equalsIgnoreCase("") || (empCountry.equals(null)))
			throw new NullPointerException("Employee Country can't be NULL or Blank");
		if (empAge==null||empAge==0||empAge<0)
			throw new NullPointerException("Employee Age can't be NULL or Blank or less than 0");
		if(CommonUtils.verifyExistenceOfData(employeeList, Employee::getEmployeeID, empID).size()>0)
			throw new DuplicateDataException("Employee ID already exists");
		try {
			employeeList.add(empObj);
			allEmployeesWrapper.setEmployees(employeeList);
		} catch (Exception e) {
			throw new ServerException("Exception in :" + Thread.currentThread().getStackTrace()[2].getMethodName());
		}
		return empObj;
	}
	
	public Object getEmployeeDetailsByID(Integer empID) throws DataNotFoundException {
		if (empID==null||empID==0||empID<0)
			throw new NullPointerException("Employee ID can't be NULL or Blank or less than 0");
		List<Employee> filterEmployeeList = CommonUtils.verifyExistenceOfData(employeeList, Employee::getEmployeeID, empID);
		if(filterEmployeeList.size()<=0 ||filterEmployeeList==null)
			throw new DataNotFoundException("No Employee Information present for this ID");
		else
			return filterEmployeeList.stream().findFirst().get();
	}
	
	public EmployeeList getEmployeeDetailsByDept(String empDept) throws DataNotFoundException {
		List<Employee> filterEmpLis = allEmployeesWrapper.getEmployees()
				.stream()
				.filter(empObj->empObj.getEmployeeDept().equalsIgnoreCase(empDept))
				.collect(Collectors.toList());
		if(filterEmpLis.size()<=0 ||filterEmpLis==null)
			throw new DataNotFoundException("No Employee Information present for this Department");
		else 
			return new EmployeeList(filterEmpLis);
	}
	
	public EmployeeList getEmployeesBetweenSpecificAges(Integer minAge, Integer maxAge) throws DataValueException, DataNotFoundException {
		if(minAge<=0 || maxAge<=0)
			throw new DataValueException("Max and Min age should more than 0.");
		if(minAge==null || maxAge==null )
			throw new NullPointerException("Max and Min age shouldn't be Null");
		if(maxAge.equals(minAge))
			throw new DataValueException("Max and Min age can't be same");
		if(maxAge<minAge)
			throw new DataValueException("Max Age shouldn't be smaller than Min Age");
		List<Employee> filteredEmpList = employeeList.stream()
				.filter(emp->emp.getEmployeeAge()>=minAge && emp.getEmployeeAge()<=maxAge)
				.collect(Collectors.toList());
		if(filteredEmpList.size()<=0 ||filteredEmpList==null)
			throw new DataNotFoundException("No Employee Information present for this Department");
		else 
			return new EmployeeList(filteredEmpList);
	}
	
	public Employee updateEmployeeRecords(Integer empID, Employee empObj) throws DataNotFoundException {
		if(empObj==null || empID==null)
			throw new NullPointerException("Null Value is not accepted for Updation");
		try {
				return employeeList.stream().filter(emp->emp.getEmployeeID().equals(empID)).map(empObject ->{
				empObject.setEmployeeName(empObj.getEmployeeName());
				empObject.setEmployeeDept(empObj.getEmployeeDept());
				empObject.setEmployeeAge(empObj.getEmployeeAge());
				empObject.setEmployeeAddress(empObj.getEmployeeAddress());
				return empObject;
		}).findAny().get();
		}catch(Exception e) {
			throw new DataNotFoundException("No Employee information found for specific Employee ID. Hence not able to update");
		}
	}
	
	public String deleteEmployeeDetailByID(Integer employeeID) throws DataNotFoundException {
		if (employeeID==null||employeeID==0||employeeID<0)
			throw new NullPointerException("Employee ID can't be NULL or Blank or less than 0");
		try {
				return employeeList.removeIf((Employee e) ->e.getEmployeeID().equals(employeeID))?"Employee Record Removed successfully":"No Employee information found for specific Employee ID. Hence not able to Delete";
		}catch(Exception e) {
			throw new DataNotFoundException("No Employee information found for specific Employee ID. Hence not able to Delete");
		}
	}
}
