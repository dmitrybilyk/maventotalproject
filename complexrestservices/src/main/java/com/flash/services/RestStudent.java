package com.flash.services;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by bid on 8/5/14.
 */
@Path("/student")
public interface RestStudent
{
   public void printStudent();

   public Response createStudent();
}
