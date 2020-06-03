package com.employee.ExceptionMapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import com.employee.ExceptionHandler.DataNotFoundException;
import com.employee.ModelClass.ErrorModel;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>{

	@Override
	public Response toResponse(DataNotFoundException exception) {
		ErrorModel err = new ErrorModel(Status.NOT_FOUND, exception.getMessage());
		return Response.status(Status.NOT_FOUND).entity(err).type(MediaType.APPLICATION_JSON).build();
	}
}
