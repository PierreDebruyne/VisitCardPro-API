package com.visitcardpro.services;

import com.visitcardpro.beans.User;

import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/cards")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CardService {

    @Context
    ContainerRequestContext requestContext;

    @GET
    public Response exploreCards() {
        String user = (String) requestContext.getProperty("user");
        if (user == null)
            return Response.ok().entity("lol").build();
        else
            return Response.ok().entity(user).build();
    }

    @POST
    public Response createCard() {
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    public Response updateCard() {
        return Response.ok().build();
    }

    @DELETE
    public Response deleteCard() {
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    public Response getCardById(@PathParam("id") final String id) {
        return Response.ok().entity(null).build();
    }

}
