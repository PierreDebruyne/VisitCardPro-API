package com.visitcardpro.services;

import com.visitcardpro.beans.SharedCard;
import com.visitcardpro.beans.User;
import com.visitcardpro.dao.DAOFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/contacts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ContactService {
    @Context
    private ContainerRequestContext requestContext;
    @Context
    private HttpServletRequest servletRequest;

    @POST
    public Response addContact(@QueryParam("sharingKey") final String sharingKey) {
        User user = (User) servletRequest.getSession().getAttribute("user");
        SharedCard card = DAOFactory.getInstance().getSharedCardDao().createByUser(sharingKey, user);
        return Response.ok().entity(card).build();
    }

    @GET
    public Response getContacts() {
        User user = (User) servletRequest.getSession().getAttribute("user");
        List<SharedCard> cards = DAOFactory.getInstance().getSharedCardDao().getByUser(user);
        return Response.ok().entity(cards).build();
    }

    @DELETE
    public Response removeContact(@QueryParam("sharingKey") final String sharingKey) {
        User user = (User) servletRequest.getSession().getAttribute("user");
        DAOFactory.getInstance().getSharedCardDao().deleteByKeyAndUser(sharingKey, user);
        return Response.ok().build();
    }
}
