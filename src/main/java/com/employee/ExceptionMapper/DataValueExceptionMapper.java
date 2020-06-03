package com.employee.ExceptionMapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import com.employee.ExceptionHandler.DataValueException;
import com.employee.ModelClass.ErrorModel;

@Provider
public class DataValueExceptionMapper implements ExceptionMapper<DataValueException>{

	public Response toResponse(DataValueException exception) {
		ErrorModel err = new ErrorModel(Status.NOT_ACCEPTABLE, exception.getMessage());
		return Response.status(Status.NOT_ACCEPTABLE).entity(err).type(MediaType.APPLICATION_JSON).build();
	}

}
