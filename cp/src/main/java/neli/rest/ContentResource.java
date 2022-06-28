package neli.rest;

import neli.model.CPNegociationTrigger;
import neli.service.CPNegotiationService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Rest Layer to receive a request for content brokering
 */

@Path("/")
public class ContentResource {


    @Inject
    CPNegotiationService cpService;

    @Path("content")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response negociateContent(CPNegociationTrigger trigger) {
        cpService.processNegotiationTrigger(trigger);
        return Response.accepted().build();

    }

    @Path("negotiations")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSuccesfulNegotiation() {
        return Response.ok(cpService.getNegotiations()).build();
    }
}