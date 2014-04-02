package org.apache.camel.example.order;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;

/**
 * Created by dmitry.bilyk on 2/28/14.
 */

@Produces(value = {APPLICATION_XML, APPLICATION_JSON})
@Consumes(value = {APPLICATION_XML, APPLICATION_JSON})
@WebService
@Path("/myRest")
public interface MyRestEndpoint {

    String myRequest(String partName, String lastName, int amount, String customerName);
}
