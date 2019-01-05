package com.visitcardpro.services;

import com.visitcardpro.authentication.Authenticated;
import com.visitcardpro.beans.SharedCard;
import com.visitcardpro.beans.User;
import com.visitcardpro.dao.DAOFactory;
import com.visitcardpro.utils.RandomString;

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
        SharedCard sCard = new SharedCard();
        sCard.setKey(new RandomString(8).nextString());
        DAOFactory.getInstance().getSharedCardDao().createByUserAndSharingKey(sCard, user, sharingKey);
        return Response.ok().entity(sCard).build();
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

    @GET
    @Path("/{key}")
    @Authenticated
    public Response getContact(@PathParam("key") final String key) {
        User user = (User) servletRequest.getSession().getAttribute("user");
        SharedCard card = DAOFactory.getInstance().getSharedCardDao().getByKeyAndUser(key, user);
        return Response.ok().entity(card).build();
    }
}
