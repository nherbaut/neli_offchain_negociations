package neli.rest;

import neli.service.CPInitiateNegotiationService;
import neli.model.CPNegociationTrigger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Rest Layer to receive a request for content brokering
 */
@Path("/content")
public class ContentResource {


    @Inject
    CPInitiateNegotiationService cpService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response negociateContent(CPNegociationTrigger trigger) {
        cpService.processNegotiationTrigger(trigger);
        return Response.accepted().build();

    }
}