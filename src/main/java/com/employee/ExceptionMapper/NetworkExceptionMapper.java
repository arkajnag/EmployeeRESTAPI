package com.employee.ExceptionMapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import com.employee.ExceptionHandler.NetworkException;
import com.employee.ModelClass.ErrorModel;

@Provider
public class NetworkExceptionMapper implements ExceptionMapper<NetworkException>{

	@Override
	public Response toResponse(NetworkException exception) {
		ErrorModel err = new ErrorModel(Status.BAD_GATEWAY, exception.getMessage());
		return Response.status(Status.BAD_GATEWAY).entity(err).type(MediaType.APPLICATION_JSON).build();
	}

}
