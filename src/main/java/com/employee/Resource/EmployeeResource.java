package com.employee.Resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.employee.ExceptionHandler.DataNotFoundException;
import com.employee.ExceptionHandler.DataValueException;
import com.employee.ExceptionHandler.DuplicateDataException;
import com.employee.ExceptionHandler.ServerException;
import com.employee.ModelClass.Employee;
import com.employee.ModelClass.EmployeeList;
import com.employee.Services.EmployeeService;

@Path("/emp")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeResource {

	private EmployeeService empServcice = new EmployeeService();
	
	@GET
	public Object getEmployees(@QueryParam("empDept") String empDept) throws DataNotFoundException {
		return empDept!=null?empServcice.getEmployeeDetailsByDept(empDept):empServcice.getAllEmployeeDetails();
	}
	
	@POST
	public Employee postNewEmployeeData(Employee empObj) throws DuplicateDataException, ServerException {
		return empServcice.postNewEmployeeDetails(empObj);
	}
	
	@GET
	@Path("/{employeeID}")
	public Object getEmployeeDetailsByID(@PathParam("employeeID")Integer employeeID, @Context UriInfo info) throws DataNotFoundException {
		return empServcice.getEmployeeDetailsByID(employeeID);	
	}
	
	@GET
	@Path("/search")
	public EmployeeList getEmployeeDetailsWithinAges(@Context UriInfo info) throws DataValueException, DataNotFoundException {
		Integer maxAge = Integer.parseInt(info.getQueryParameters().getFirst("maxAge"));
		Integer minAge = Integer.parseInt(info.getQueryParameters().getFirst("minAge"));
		return empServcice.getEmployeesBetweenSpecificAges(minAge, maxAge);
	}
	
	@PUT
	@Path("/{empID}")
	public Employee updateEmployeeDetailsByID(@PathParam("empID") Integer empID, Employee empObj) throws DataNotFoundException {
		return empServcice.updateEmployeeRecords(empID,empObj);
	}
	
	@DELETE
	@Path("/{empID}")
	public String deleteEmployeeRecord(@PathParam("empID") Integer empID) throws DataNotFoundException {
		return empServcice.deleteEmployeeDetailByID(empID);
	}
	
}
