package com.visitcardpro.services;

import com.visitcardpro.authentication.Authenticated;
import com.visitcardpro.beans.Card;
import com.visitcardpro.beans.PersonnalCard;
import com.visitcardpro.beans.User;
import com.visitcardpro.dao.DAOFactory;
import com.visitcardpro.forms.CardForm;
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
        List<PersonnalCard> cards = DAOFactory.getInstance().getPersonnalCardDao().getByUser(user);
        return Response.ok().entity(cards).build();
    }

    @POST
    @Authenticated
    public Response createCard(final CardForm form) {
        User user = (User) servletRequest.getSession().getAttribute("user");
        if (!form.isValidForm())
            return Response.status(Response.Status.BAD_REQUEST).entity(form.getErrors()).build();

        Card card = form.generateCard();

        PersonnalCard pCard = new PersonnalCard();
        pCard.setKey(new RandomString(8).nextString());
        pCard.setSharingKey(new RandomString(8).nextString());
        pCard.setCard(card);

        DAOFactory.getInstance().getPersonnalCardDao().createByUser(pCard, user);
        return Response.status(Response.Status.CREATED).entity(card).build();
    }

    @PUT
    @Path("/{key}")
    @Authenticated
    public Response updateCard(@PathParam("key") final String key, final CardForm form) {
        User user = (User) servletRequest.getSession().getAttribute("user");
        if (!form.isValidForm())
            return Response.status(Response.Status.BAD_REQUEST).entity(form.getErrors()).build();
        Card card = form.generateCard();
        DAOFactory.getInstance().getPersonnalCardDao().updateCardByUserAndKey(card, user, key);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{key}")
    @Authenticated
    public Response deleteCard(@PathParam("key") final String key) {
        User user = (User) servletRequest.getSession().getAttribute("user");
        DAOFactory.getInstance().getPersonnalCardDao().deleteByKeyAndUser(key, user);
        return Response.ok().build();
    }

    @GET
    @Path("/{key}")
    @Authenticated
    public Response getCard(@PathParam("key") final String key) {
        User user = (User) servletRequest.getSession().getAttribute("user");
        PersonnalCard card = DAOFactory.getInstance().getPersonnalCardDao().getByKeyAndUser(key, user);
        return Response.ok().entity(card).build();
    }
}
