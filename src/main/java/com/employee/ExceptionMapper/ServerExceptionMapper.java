package com.employee.ExceptionMapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import com.employee.ExceptionHandler.ServerException;
import com.employee.ModelClass.ErrorModel;

@Provider
public class ServerExceptionMapper implements ExceptionMapper<ServerException>{

	@Override
	public Response toResponse(ServerException exception) {
		ErrorModel err = new ErrorModel(Status.INTERNAL_SERVER_ERROR,exception.getMessage());
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(err).type(MediaType.APPLICATION_JSON).build();
	}

}
