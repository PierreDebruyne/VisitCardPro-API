package services;

import com.visitcardpro.beans.Card;
import com.visitcardpro.forms.CardForm;
import com.visitcardpro.services.AuthenticationService;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class Cards extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(AuthenticationService.class);
    }

    @Test
    public void create() {
        CardForm form = new CardForm();
        form.setEmail("test@test.fr");
        form.setFirstName("Tester");
        form.setLastName("Tester");
        form.setPhone("0123456789");

        Response response = target("/cards").request(MediaType.APPLICATION_JSON_TYPE).header("access_token", "token")
                .post(Entity.entity(form, MediaType.APPLICATION_JSON_TYPE));
        Assert.assertEquals("Http Response should be 201 ", Response.Status.CREATED.getStatusCode(), response.getStatus());

    }

/*    @Test
    public void update() {
        Response response = target("/auth/test").request().get();

        Assert.assertEquals("Http Response should be 200 ", Response.Status.OK.getStatusCode(), response.getStatus());

    }

    @Test
    public void explore() {
        Response response = target("/auth/test").request().get();

        Assert.assertEquals("Http Response should be 200 ", Response.Status.OK.getStatusCode(), response.getStatus());

    }

    @Test
    public void get() {
        Response response = target("/auth/test").request().get();

        Assert.assertEquals("Http Response should be 200 ", Response.Status.OK.getStatusCode(), response.getStatus());

    }

    @Test
    public void delete() {
        Response response = target("/auth/test").request().get();

        Assert.assertEquals("Http Response should be 200 ", Response.Status.OK.getStatusCode(), response.getStatus());

    }*/
}
