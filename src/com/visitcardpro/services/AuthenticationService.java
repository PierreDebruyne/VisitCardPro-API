package com.visitcardpro.services;

import com.visitcardpro.authentication.Authenticated;
import com.visitcardpro.authentication.TokenHelper;
import com.visitcardpro.beans.Authentication;
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

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/auth")
public class AuthenticationService {

    @Context
    private ContainerRequestContext requestContext;
    @Context
    private HttpServletRequest servletRequest;

    @POST
    @Path("/signin")
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
        if (!BCrypt.checkpw(password, user.getAuth().getHashedPassword())) {
            return Response.status(Response.Status.BAD_REQUEST).entity("invalid password").build();
        }

        String accessToken =  TokenHelper.generateAccessToken(email);

        servletRequest.getSession().setAttribute("accessToken", accessToken);
        servletRequest.getSession().setAttribute("user", user);

        if (user.getAuth().getRefreshToken() == null) {
            System.out.println("Empty=====");
            user.getAuth().setRefreshToken(TokenHelper.generateRefreshToken());
            DAOFactory.getInstance().getUserDao().update(user);
        }
        return Response.ok().header("access_token", accessToken).header("refresh_token", user.getAuth().getRefreshToken()).build();
    }

    @GET
    @Path("/refresh")
    public Response getNewAccessToken(@HeaderParam("refresh_token") final String refreshToken) {

        User user = DAOFactory.getInstance().getUserDao().findByRefreshToken(refreshToken);
        if (user == null)
            return Response.status(Response.Status.BAD_REQUEST).entity("invalid refresh token").build();
        String accessToken =  TokenHelper.generateAccessToken(user.getAuth().getEmail());
        servletRequest.getSession().setAttribute("user", user);
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
        servletRequest.getSession().invalidate();
        return Response.ok().build();
    }

    @POST
    @Path("/signup")
    public Response signup(@HeaderParam("Authorization") final String credential) {
        // validate form
        final String encodedUserPassword = credential.replaceFirst("Basic"+ " ", "");
        String email = JobHelper.getCredentialParam(encodedUserPassword, 1);
        String password = JobHelper.getCredentialParam(encodedUserPassword, 2);

        String salt = BCrypt.gensalt(12);
        String hashed = BCrypt.hashpw(password, salt);

        Authentication auth = new Authentication();
        auth.setRefreshToken(null);
        auth.setEmail(email);
        auth.setHashedPassword(hashed);
        auth.setRole("BASIC");

        User user = new User();
        user.setAuth(auth);

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

    @PUT
    @Authenticated
    @Path("/editPassword")
    public Response modifyPassword(@HeaderParam("oldPassword") final String oldPassword, @HeaderParam("newPassword") final String newPassword) {
        User user = (User) servletRequest.getSession().getAttribute("user");

        if (!BCrypt.checkpw(oldPassword, user.getAuth().getHashedPassword())) {
            return Response.status(Response.Status.BAD_REQUEST).entity("invalid password").build();
        }
        String salt = BCrypt.gensalt(12);
        String newHashedPassowrd = BCrypt.hashpw(newPassword, salt);

        user.getAuth().setHashedPassword(newHashedPassowrd);
        DAOFactory.getInstance().getAuthenticationDao().update(user.getAuth());

        return Response.ok().build();
    }

    @POST
    @Path("/resetPassword/{resetPasswordToken}")
    public Response passwordReset(@HeaderParam("newPassword") final String newPassword, @PathParam("resetPasswordToken") final String resetPasswordToken) {
        String salt = BCrypt.gensalt(12);
        String hashedPassword = BCrypt.hashpw(newPassword, salt);

        DAOFactory.getInstance().getUserDao().updatePasswordByResetPasswordToken(resetPasswordToken, hashedPassword);
        return Response.ok().build();
    }

    @POST
    @Path("/forgetPassword")
    public Response passwordForget(@HeaderParam("email") final String email) {
        String resetPasswordToken = TokenHelper.generateResetPasswordToken();
        DAOFactory.getInstance().getUserDao().updateResetPasswordTokenByEmail(email, resetPasswordToken);
        return Response.ok().entity("/resetPassword/" + resetPasswordToken).build();
    }

}
