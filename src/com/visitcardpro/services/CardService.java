package com.visitcardpro.services;

import com.visitcardpro.authentication.Authenticated;
import com.visitcardpro.beans.Card;
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

@Path("/cards")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CardService {

    @Context
    private ContainerRequestContext requestContext;
    @Context
    private HttpServletRequest servletRequest;

    @GET
    @Authenticated
    public Response exploreCards() {
        User user = (User) servletRequest.getSession().getAttribute("user");
        List<Card> cards = DAOFactory.getInstance().getCardDao().getCardsByUser(user);
        return Response.ok().entity(cards).build();
    }



    @POST
    @Authenticated
    public Response createCard(final Card form) {
        form.setKey(new RandomString(8).nextString());
        User user = (User) servletRequest.getSession().getAttribute("user");
        DAOFactory.getInstance().getCardDao().createCardByUser(form, user);
        return Response.status(Response.Status.CREATED).header("key", form.getKey()).build();
    }

    @PUT
    @Path("/{key}")
    public Response updateCard(@PathParam("key") final String key, final Card form) {
        User user = (User) servletRequest.getSession().getAttribute("user");
        DAOFactory.getInstance().getCardDao().updateCardByUser(form, user);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCard(@PathParam("id") final String id) {
        return Response.ok().build();
    }

    @GET
    @Path("/{key}")
    public Response getCard(@PathParam("key") final String key) {
        User user = (User) servletRequest.getSession().getAttribute("user");
        Card card = DAOFactory.getInstance().getCardDao().getCardByKeyAndUser(key, user);
        if (card == null)
            return Response.status(Response.Status.BAD_REQUEST).entity("Card doesn't exist.").build();
        return Response.ok().entity(null).build();
    }
}
