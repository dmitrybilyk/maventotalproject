package com.flash.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Created by bid on 8/5/14.
 */
@Path("/")
public interface RestStudent
{
   public void printStudent();

   @Path("create")
   @Produces("application/json;charset=UTF-8")
   public Response createStudent();
}
