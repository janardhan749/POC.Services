package com.mypractice.in.FirstProject;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

public class Resource {

	/*@POST
	@Path("/{admin}")
	@Produces({"application/json", "application/xml", "application/problem+json" })
	@Consumes({"application/json", "application/xml", "application/problem+json" })
	public Response UserDetails (Request req){
			//@QueryParam("") ServiceNameRq uname) {
		// this does nothing -- just a shell for CXF and Camel
		return null;
	}*/	
	@GET
	@Path("/{admin}")
	@Produces({"application/json", "application/xml", "application/problem+json" })
	@Consumes({"application/json", "application/xml", "application/problem+json" })
	public Response UserDetails (@PathParam("admin") String admin){
			//@QueryParam("") ServiceNameRq uname) {
		// this does nothing -- just a shell for CXF and Camel
		return null;
	}
}
