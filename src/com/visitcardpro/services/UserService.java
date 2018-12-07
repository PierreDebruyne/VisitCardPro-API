package com.visitcardpro.services;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserService {

    @POST
    @Path("/signin")
    public Response signin() {
        return Response.ok().build();
    }

    @POST
    @Path("/signout")
    public Response signout() {
        return Response.ok().build();
    }

    @POST
    @Path("/signup")
    public Response signup() {
        return Response.status(Response.Status.CREATED).build();
    }


}
