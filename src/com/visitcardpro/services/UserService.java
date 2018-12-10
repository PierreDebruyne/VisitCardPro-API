package com.visitcardpro.services;

import com.visitcardpro.authentication.Authenticated;
import com.visitcardpro.authentication.TokenHelper;
import com.visitcardpro.beans.Authentication;
import com.visitcardpro.beans.User;
import com.visitcardpro.dao.DAOFactory;
import com.visitcardpro.utils.JobHelper;
import com.visitcardpro.utils.RandomString;
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

        User user = DAOFactory.getInstance().getUserDao().findByEmail(email); // get user by email from DAO

        if (user == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("invalid login").build();
        }
        if (BCrypt.checkpw(password, user.getAuth().getHashedPassword())) {
            return Response.status(Response.Status.BAD_REQUEST).entity("invalid password").build();
        }

        String accessToken =  TokenHelper.generateAccessToken(email);

        servletRequest.getSession().setAttribute("accessToken", accessToken);
        servletRequest.getSession().setAttribute("user", user);

        if (user.getAuth().getRefreshToken() == null) {
            user.getAuth().setRefreshToken(TokenHelper.generateRefreshToken());
            DAOFactory.getInstance().getUserDao().update(user);
        }
        return Response.ok().header("access_token", accessToken).header("refresh_token", user.getAuth().getRefreshToken()).build();
    }

    @GET
    @Path("/token")
    public Response getNewAccessToken(@HeaderParam("refresh_token") final String refreshToken) {

        User user = DAOFactory.getInstance().getUserDao().findByRefreshToken(refreshToken);
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
        User user = (User) servletRequest.getSession().getAttribute("user");
        user.getAuth().setRefreshToken(null);
        DAOFactory.getInstance().getUserDao().update(user);
        return Response.ok().build();
    }

    @POST
    @Path("/signup")
    public Response signup(final User form, @HeaderParam("Authorization") final String credential) {
        // validate form
        String email = JobHelper.getCredentialParam(credential, 1);
        String password = JobHelper.getCredentialParam(credential, 2);

        String salt = BCrypt.gensalt(12);
        String hashed = BCrypt.hashpw(password, salt);

        Authentication auth = new Authentication();
        auth.setRefreshToken(TokenHelper.generateRefreshToken());
        auth.setEmail(email);
        auth.setHashedPassword(hashed);
        auth.setRole("BASIC");

        form.setAuth(auth);

        DAOFactory.getInstance().getAuthenticationDao().create(auth);
        DAOFactory.getInstance().getUserDao().create(form);
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
    @Authenticated
    @Path("/password-modify")
    public Response modifyPassword(@HeaderParam("Authorization") final String credential) {
        String oldPassword = JobHelper.getCredentialParam(credential, 1);
        String newPassword = JobHelper.getCredentialParam(credential, 2);

        String salt = BCrypt.gensalt(12);
        String hashed = BCrypt.hashpw(oldPassword, salt);

        User user = (User) servletRequest.getSession().getAttribute("user");
        if (!user.getAuth().getHashedPassword().equals(hashed))
            return Response.status(Response.Status.BAD_REQUEST).entity("Incorrect old password").build();

        salt = BCrypt.gensalt(12);
        hashed = BCrypt.hashpw(newPassword, salt);

        user.getAuth().setHashedPassword(hashed);
        DAOFactory.getInstance().getAuthenticationDao().update(user.getAuth());

        return Response.ok().build();
    }

    @POST
    @Path("/password-reset")
    public Response passwordReset() {
        return Response.ok().build();
    }

    @POST
    @Path("/password-forget")
    public Response passwordForget() {
        String key = TokenHelper.generateResetPasswordToken();

        // store key in auth
        // return generated URI
        return Response.ok().build();
    }

}
