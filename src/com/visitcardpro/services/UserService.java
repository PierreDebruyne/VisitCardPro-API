package com.visitcardpro.services;

import com.visitcardpro.authentication.TokenHelper;
import com.visitcardpro.beans.User;
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
        if (credential == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        final String encodedUserPassword = credential.replaceFirst("Basic"+ " ", "");
        String usernameAndPassword = null;
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(encodedUserPassword);
            usernameAndPassword = new String(decodedBytes, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
        String email = "";
        String password = "";
        if (tokenizer.hasMoreTokens()) {
            email = tokenizer.nextToken();
        } if (tokenizer.hasMoreTokens()) {
            password = tokenizer.nextToken();
        }

        User user = null; // get user by email from DAO

        if (user == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("invalid login").build();
        }
        if (BCrypt.checkpw(password, user.getAuth().getHashedPassword())) {
            return Response.status(Response.Status.BAD_REQUEST).entity("invalid password").build();
        }
        if (servletRequest.getSession().getAttribute("accessToken") != null) {
            // delete old refresh token - create new - store new token in DB
        }

        String accessToken =  TokenHelper.generateAccessToken(email);
        servletRequest.getSession().setAttribute("accessToken", accessToken);
        servletRequest.getSession().setAttribute("userId", user.getId());

        return Response.ok().header("access_token", accessToken).header("refresh_token", user.getAuth().getRefreshToken()).build();
    }

    @GET
    @Path("/token")
    public Response getNewAccessToken(@HeaderParam("refresh_token") final String refreshToken) {

        User user = null; // get user from refresh_token
        if (user == null)
            return Response.status(Response.Status.BAD_REQUEST).entity("invalid refresh token").build();
        String accessToken =  TokenHelper.generateAccessToken(user.getAuth().getEmail());
        servletRequest.getSession().setAttribute("accessToken", accessToken);
        return Response.ok().header("access_token", accessToken).build();
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
