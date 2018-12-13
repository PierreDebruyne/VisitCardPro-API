package com.visitcardpro.filters;

import com.visitcardpro.authentication.Authenticated;
import com.visitcardpro.authentication.TokenHelper;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Method;
import java.util.Date;

@Provider
public class AuthenticationFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    @Context
    private HttpServletRequest servletRequest;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        Method method = resourceInfo.getResourceMethod();
        if (method.isAnnotationPresent(Authenticated.class)) {
            String token = requestContext.getHeaderString("access_token");

            if (token == null || token.isEmpty()) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Authentication required : access_token not found or empty").build());
            } else {
                TokenHelper helper = new TokenHelper(token);

                if (new Date().after(helper.getExpiration())) {
                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Authentication required : access_token expired").build());
                } else if (servletRequest.getSession().getAttribute("user") == null) {
                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Authentication required : not authenticated").build());
                } else if (!servletRequest.getSession().getAttribute("accessToken").equals(token)) {
                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Authentication required : access_token doesn't match").build());
                }
            }
        }
    }
}