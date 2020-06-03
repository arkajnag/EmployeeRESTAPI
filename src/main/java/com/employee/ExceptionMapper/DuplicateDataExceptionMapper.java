package com.employee.ExceptionMapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import com.employee.ExceptionHandler.DuplicateDataException;
import com.employee.ModelClass.ErrorModel;

@Provider
public class DuplicateDataExceptionMapper implements ExceptionMapper<DuplicateDataException>{

	@Override
	public Response toResponse(DuplicateDataException exception) {
		ErrorModel err = new ErrorModel(Status.CONFLICT, exception.getMessage());
		return Response.status(Status.CONFLICT).entity(err).type(MediaType.APPLICATION_JSON).build();
	}

}
