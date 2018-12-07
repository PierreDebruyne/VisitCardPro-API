package com.visitcardpro.filters;

import com.visitcardpro.beans.User;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

@Provider
public class AuthenticationFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    @Context
    private HttpServletRequest servletRequest;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        //Method method = resourceInfo.getResourceMethod();
        //String token = requestContext.getHeaderString("accessToken");
        // get user from accessToken

        requestContext.setProperty("user", servletRequest.getSession().getId());

    }
}