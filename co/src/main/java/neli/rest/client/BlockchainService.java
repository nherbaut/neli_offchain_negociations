package neli.rest.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import java.util.Map;

@Path("/co/broadcast")
@RegisterRestClient
public interface BlockchainService {
    @PUT
    @Path("/addContent")

    Map<String, Object> coAddContent(Map<String, Object> availableContent);
}