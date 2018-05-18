package com.mindtree.shoppingkart.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mindtree.shoppingkart.service.EKartService;

@Component
@Path("/test")
public class TestResource {
	
	private static final Logger log = Logger.getLogger(TestResource.class);
	
	@Autowired
	private EKartService eKarkService;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response test() {

		String result = eKarkService.test();
		log.info("Saving Categories");
		return Response.status(200).entity(result).build();

	}
}
