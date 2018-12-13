package services;

import com.visitcardpro.services.AuthenticationService;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.util.List;

public class Authentication extends JerseyTest {
    @Override
    protected Application configure() {
        return new ResourceConfig(AuthenticationService.class);
    }

    @Test
    public void signup() {
        Response response = target("/auth/signup").request().header("Authorization", "Basic aHVnby53YWxiZWNxQGdtYWlsLmNvbTp4dHhud2ZsZF9f").post(null);
        Assert.assertEquals("Http Response should be 200", Response.Status.OK.getStatusCode(), response.getStatus());

    }

    @Test
    public void test() {
        Response response = target("/auth/test").request().get();
        Assert.assertEquals("Http Response should be 200 ", Response.Status.OK.getStatusCode(), response.getStatus());

    }
/*    @Test
    public void signin() {
        Response response = target("/auth/signin").request().header("Authorization", "Basic aHVnby53YWxiZWNxQGdtYWlsLmNvbTp4dHhud2ZsZF9f").post(null);
        Assert.assertEquals("Http Response should be 200 ", Response.Status.OK.getStatusCode(), response.getStatus());

    }

    @Test
    public void refresh() {
        Response response = target("/auth/refresh").request().header("refresh_token", "token").get();

        Assert.assertEquals("Http Response should be 200 ", Response.Status.OK.getStatusCode(), response.getStatus());

    }

    @Test
    public void signout() {
        Response response = target("/auth/signout").request().header("access_token", "token").get();

        Assert.assertEquals("Http Response should be 200 ", Response.Status.OK.getStatusCode(), response.getStatus());

    }

    @Test
    public void modifyPassword() {
        Response response = target("/auth/test").request().get();

        Assert.assertEquals("Http Response should be 200 ", Response.Status.OK.getStatusCode(), response.getStatus());

    }

    @Test
    public void passwordForget() {
        Response response = target("/auth/test").request().get();

        Assert.assertEquals("Http Response should be 200 ", Response.Status.OK.getStatusCode(), response.getStatus());

    }

    @Test
    public void resetPassword() {
        Response response = target("/auth/test").request().get();

        Assert.assertEquals("Http Response should be 200 ", Response.Status.OK.getStatusCode(), response.getStatus());

    }*/
}
