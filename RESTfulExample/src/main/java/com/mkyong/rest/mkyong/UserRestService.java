package com.mkyong.rest.mkyong;

import com.mkyong.rest.Track;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.util.List;

@Path("/rest/users")
public class UserRestService {

    @GET
    public Response getUser() {
        http://localhost:8080/RESTfulExample/users
        return Response.status(200).entity("getUser is called").build();

    }

    @GET
    @Path("/vip")
    public Response getUserVIP() {
//http://localhost:8080/RESTfulExample/users/vip
        return Response.status(200).entity("getUserVIP is called").build();

    }

    @GET
    @Path("{name}/{name2}")
    public Response getUserByName(@PathParam("name") String name, @PathParam("name2") String name2) {
//http://localhost:8080/RESTfulExample/users/val
        return Response.status(200)
                .entity("getUserByName is called, name : " + name +"name2 = " +name2).build();

    }



    @GET
    @Path("{id : \\d+}") //support digit only
    public Response getUserById(@PathParam("id") String id) {

        return Response.status(200).entity("getUserById is called, id : " + id).build();

    }

    @GET
    @Path("/username/{username : [a-zA-Z][a-zA-Z_0-9]}")
    public Response getUserByUserName(@PathParam("username") String username) {

        return Response.status(200)
                .entity("getUserByUserName is called, username : " + username).build();

    }

    @GET
    @Path("/books/{isbn : \\d+}")
    public Response getUserBookByISBN(@PathParam("isbn") String isbn) {

        return Response.status(200)
                .entity("getUserBookByISBN is called, isbn : " + isbn).build();

    }


    @GET
    @Path("/query")
    public Response getUsers(
            @QueryParam("from") int from,
            @QueryParam("to") int to,
            @QueryParam("orderBy") List<String> orderBy) {
//http://localhost:8080/RESTfulExample/users/query?from=100&to=200&orderBy=age&orderBy=name
        return Response
                .status(200)
                .entity("getUsers is called, from : " + from + ", to : " + to
                        + ", orderBy" + orderBy.toString()).build();

    }



    @POST
    @Path("/add")
    public Response addUser(
            @FormParam("name") String name,
            @FormParam("age") int age) {

        return Response.status(200)
                .entity("addUser is called, name : " + name + ", age : " + age)
                .build();

    }

    @GET
    @Path("/getHeader")
    public Response addUser(@Context HttpHeaders headers) {

        String userAgent = headers.getRequestHeader("user-agent").get(0);
//http://localhost:8080/RESTfulExample/rest/users/getHeader
        return Response.status(200)
                .entity("addUser is called, userAgent : " + userAgent)
                .build();

    }


    private static final String FILE_PATH = "form.html";

    @GET
    @Path("/getFile")
    @Produces("text/plain")
    public Response getFile() {

        File file = new File(FILE_PATH);

        Response.ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition",
                "attachment; filename=\"file_from_server.log\"");
        return response.build();

    }


    @GET
    @Path("/getTrack")
    @Produces(MediaType.APPLICATION_JSON)
    public Track getTrackInJSON() {

        Track track = new Track();
        track.setTitle("Enter Sandman");
        track.setSinger("Metallica");

        return track;

    }

    @POST
    @Path("/postTrack")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTrackInJSON(Track track) {

        String result = "Track saved : " + track;
        return Response.status(201).entity(result).build();

    }

}