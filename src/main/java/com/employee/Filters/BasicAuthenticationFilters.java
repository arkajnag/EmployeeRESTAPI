package com.employee.Filters;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.apache.commons.codec.binary.Base64;
import com.employee.ModelClass.ErrorModel;

public class BasicAuthenticationFilters implements ContainerResponseFilter{

	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String AUTHORIZATION_TEXT = "Basic ";
	private static final String userid = "user";
	private static final String pwd = "password123";
	
	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		List<String> authHeaders = requestContext.getHeaders().get(AUTHORIZATION_HEADER);
		if(authHeaders!=null && authHeaders.size()>0) {
			String authToken = authHeaders.get(0);
			authToken = authToken.replace(AUTHORIZATION_TEXT, "");
			byte[] byteArray = Base64.decodeBase64(authToken.getBytes());
			String decodedString = new String(byteArray);
			String[] strAuthoken = decodedString.split(":");
			String username = strAuthoken[0];
			String password = strAuthoken[1];
			if(username.equals(userid) && password.equals(pwd)) {
				return;
			}
		}
		ErrorModel message = new ErrorModel(Status.UNAUTHORIZED, "User is not Authorized. Please verify User Authentication");
		Response unauthorizedStatus = Response.status(Status.UNAUTHORIZED)
				.entity(message)
				.type(MediaType.APPLICATION_JSON)
				.build();
		requestContext.abortWith(unauthorizedStatus);
	}
}
