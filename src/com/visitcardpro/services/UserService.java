package com.visitcardpro.services;

import com.visitcardpro.beans.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserService {

    @POST
    @Path("/signin")
    public Response signin(@HeaderParam("Authorization") final String credential) {
        return Response.ok().build();
    }

    @POST
    @Path("/signout")
    public Response signout() {
        return Response.ok().build();
    }

    @POST
    @Path("/signup")
    public Response signup(final User user) {
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    public Response deleteAccount() {
        return Response.ok().build();
    }

    @POST
    public Response verifyAccount() {
        return Response.ok().build();
    }

    @POST
    public Response resetPassword() {
        return Response.ok().build();
    }

    @POST
    public Response passwordForget() {
        return Response.ok().build();
    }

}
