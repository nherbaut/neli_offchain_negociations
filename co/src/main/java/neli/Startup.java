package neli;

import io.quarkus.runtime.StartupEvent;
import neli.rest.client.BlockchainService;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class Startup {

    @Inject
    @RestClient
    BlockchainService service;

    void onstart(@Observes StartupEvent ev){
        Map<String, Object> jsonToSend = new HashMap<String, Object>() {{
            put("contentID", "12345");
            put("owner", "co0");
            put("contentName", "vid0");
        }};
        Map<String, Object> response = service.coAddContent(jsonToSend);
        response.forEach((key, value)-> System.out.println(key + ": " + value));
    }
}
