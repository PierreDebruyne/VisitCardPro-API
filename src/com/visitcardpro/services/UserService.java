package com.visitcardpro.services;

import com.visitcardpro.authentication.Authenticated;
import com.visitcardpro.authentication.TokenHelper;
import com.visitcardpro.beans.User;
import com.visitcardpro.dao.DAOFactory;
import com.visitcardpro.utils.JobHelper;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Base64;
import java.util.StringTokenizer;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserService {

    @Context
    private ContainerRequestContext requestContext;
    @Context
    private HttpServletRequest servletRequest;

    @POST
    @Path("/signin/credential")
    public Response signinWithCredential(@HeaderParam("Authorization") final String credential) {
        final String encodedUserPassword = credential.replaceFirst("Basic"+ " ", "");
        String email = JobHelper.getCredentialParam(encodedUserPassword, 1);
        String password = JobHelper.getCredentialParam(encodedUserPassword, 2);
        if (email == null || password == null)
            return Response.status(Response.Status.BAD_REQUEST).build();

        User user = DAOFactory.getInstance().getUserDao().findById(email); // get user by email from DAO

        if (user == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("invalid login").build();
        }
        if (BCrypt.checkpw(password, user.getAuth().getHashedPassword())) {
            return Response.status(Response.Status.BAD_REQUEST).entity("invalid password").build();
        }

        String accessToken =  TokenHelper.generateAccessToken(email);

        servletRequest.getSession().setAttribute("accessToken", accessToken);
        servletRequest.getSession().setAttribute("userId", user.getId());

        if (user.getAuth().getRefreshToken() == null) {
            user.getAuth().setRefreshToken(TokenHelper.generateRefreshToken());
//            DAOFactory.getInstance().getUserDao().update(user);
        }
        return Response.ok().header("access_token", accessToken).header("refresh_token", user.getAuth().getRefreshToken()).build();
    }

    @GET
    @Path("/token")
    public Response getNewAccessToken(@HeaderParam("refresh_token") final String refreshToken) {

        User user = DAOFactory.getInstance().getUserDao().findByRefreshToken(refreshToken); // get user by email from DAO
        if (user == null)
            return Response.status(Response.Status.BAD_REQUEST).entity("invalid refresh token").build();
        String accessToken =  TokenHelper.generateAccessToken(user.getAuth().getEmail());
        servletRequest.getSession().setAttribute("accessToken", accessToken);
        return Response.ok().header("access_token", accessToken).build();
    }


    @POST
    @Path("/signout")
    @Authenticated
    public Response signout() {
        User user = DAOFactory.getInstance().getUserDao().findById((Long) servletRequest.getSession().getAttribute("userId"));
        user.getAuth().setRefreshToken(null);
//            DAOFactory.getInstance().getUserDao().update(user);
        return Response.ok().build();
    }

    @POST
    @Path("/signup")
    public Response signup(final User user, @HeaderParam("Authorization") final String credential) {
        // validate form

        DAOFactory.getInstance().getUserDao().create(user);
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
