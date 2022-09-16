package neli.rest.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import java.util.Map;

@Path("/cp/broadcast")
@RegisterRestClient
public interface BlockchainService {
    @PUT
    @Path("/buyAccess")
    Map<String, Object> cpBuyAccess(Map<String, Object> availableContent);

    @PUT
    @Path("/buyService")
    Map<String, Object> cpBuyService(Map<String, Object> availableContent);
}

