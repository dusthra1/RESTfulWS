package com.mindtree.shoppingkart.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.mindtree.shoppingkart.model.ErrorMessage;

@Provider
public class UnauthorizedRequestExceptionMapper implements ExceptionMapper<UnauthorizedRequestException> {

	@Override
	public Response toResponse(UnauthorizedRequestException ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 401, "Unauthorized Request");
		return Response.status(Status.UNAUTHORIZED).entity(errorMessage).build();
	}

}
