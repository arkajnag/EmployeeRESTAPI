package com.employee.ClientTestServices;

import java.util.HashMap;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.employee.ExceptionHandler.NetworkException;
import com.employee.ModelClass.Address;
import com.employee.ModelClass.Employee;
import com.employee.ModelClass.EmployeeList;
import com.employee.Utilities.CommonUtils;
import com.employee.Utilities.WebServiceUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

public class TC_HttpVerbRequest {
	
	private String hostURI = "http://localhost:8888/Employee";
	private String serviceURI = "/app/emp";
	private Map<String,String> headers = new HashMap<>();
	SoftAssert sftAssert = new SoftAssert();
	
	@DataProvider()
	public Object[][] newEmployeeData(){
		return new Object[][] {{"Employee_A","QA",35,new Address("City_A","Country_A")},
								{"Employee_B","Dev",31,new Address("City_B","Country_B")},
								{"Employee_C","Dev",25,new Address("City_C","Country_C")},
								{"Employee_D","QA",42,new Address("City_D","Country_D")}};
	}
	
	@Test(priority = 1,description = "To post new Employee Details", dataProvider = "newEmployeeData")
	public void tcPostRequestAddNewEmployee(String name,String dept,int age, Address address) throws NetworkException {
		String endPointURI = hostURI + serviceURI;
		headers.put("Content-Type", "application/json");
		try {
				ObjectMapper mapper = new ObjectMapper();
				String jsonString = mapper.writeValueAsString(new Employee(CommonUtils.randomValue.apply(0, 9999),name,dept,age, address));
				Response response = WebServiceUtils.postRequest(endPointURI, headers, jsonString);
				Assert.assertTrue(WebServiceUtils.predHttpResponse.test(response));
		}catch(JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority = 2, description = "To fetch all the Employee Details")
	public void tcGetRequestFetchAllEmployee() throws NetworkException {
		String endPointURI = hostURI + serviceURI;
		headers.put("Content-Type", "application/json");
		Response response = WebServiceUtils.getRequest(endPointURI, headers);
		Assert.assertTrue(WebServiceUtils.predHttpResponse.test(response),"Response Code:"+response.statusCode()+" is not as expected");
		try {
				ObjectMapper mapper = new ObjectMapper();
				EmployeeList responseBody = mapper.readValue(response.asString(),EmployeeList.class);
				responseBody.getEmployees()
				.forEach(eachEmp->System.out.println(
						"Employee Name:"+eachEmp.getEmployeeName()+"  "+
						"Employee Department:"+eachEmp.getEmployeeDept()+"  "+
						"Employee Age:"+eachEmp.getEmployeeAge()+"  "+
						"Employee City:"+eachEmp.getEmployeeAddress().getCity()+
						"  Employee Country:"+eachEmp.getEmployeeAddress().getCountry()));
		}catch(JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}	
	}
	
	@DataProvider
	public Object[][] employeeInformation(){
		return new Object[][] {{"QA"},
								{"Dev"},
								{"Mon"}};
	}
	
	@Test(priority = 3, description = "To search spcecific Employee Details by Employee Department", dataProvider = "employeeInformation")
	public void tcGetSpecificEmployeeInformation(String employeeDept) {
		String queryParam = "?empDept="+employeeDept;
		String endPointURI = hostURI+serviceURI+queryParam;
		headers.put("Content-Type", "application/json");
		try {
				Response response = WebServiceUtils.getRequest(endPointURI, headers);
				Assert.assertTrue(WebServiceUtils.predHttpResponse.test(response),"Response Code:"+response.statusCode()+" is not as expected");
				ObjectMapper mapper =  new ObjectMapper();
				EmployeeList responseEmpList = mapper.readValue(response.asString(), EmployeeList.class);
				responseEmpList.getEmployees().forEach(eachEmp->System.out.println(
						"Employee Name:"+eachEmp.getEmployeeName()+"  "+
						"Employee Department:"+eachEmp.getEmployeeDept()+"  "+
						"Employee Age:"+eachEmp.getEmployeeAge()+"  "+
						"Employee City:"+eachEmp.getEmployeeAddress().getCity()+
						"  Employee Country:"+eachEmp.getEmployeeAddress().getCountry()));
		} catch (NetworkException | JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	@DataProvider
	public Object[][] getEmployeeAgeDetails(){
		return new Object[][] {{28,32},{25,45}};
	}
	@Test(priority = 4, description = "To get all Employee Information between specific Ages", dataProvider = "getEmployeeAgeDetails")
	public void tcGetEmployeesWithinAge(Integer minAge, Integer maxAge) {
		String queryParam = "/search?minAge="+minAge+"&maxAge="+maxAge;
		String endPointURI = hostURI+serviceURI+queryParam;
		headers.put("Content-Type", "application/json");
		try {
				Response response = WebServiceUtils.getRequest(endPointURI, headers);
				Assert.assertTrue(WebServiceUtils.predHttpResponse.test(response),"Response Code:"+response.statusCode()+" is not as expected");
				ObjectMapper mapper =  new ObjectMapper();
				EmployeeList responseEmpList = mapper.readValue(response.asString(), EmployeeList.class);
				responseEmpList.getEmployees().forEach(eachEmp->System.out.println(
						"Employee Name:"+eachEmp.getEmployeeName()+"  "+
						"Employee Department:"+eachEmp.getEmployeeDept()+"  "+
						"Employee Age:"+eachEmp.getEmployeeAge()+"  "+
						"Employee City:"+eachEmp.getEmployeeAddress().getCity()+
						"  Employee Country:"+eachEmp.getEmployeeAddress().getCountry()));
		}catch(NetworkException | JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}
