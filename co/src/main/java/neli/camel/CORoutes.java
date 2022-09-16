package neli.camel;

import neli.model.CPCOMessageNegotiation;
import neli.service.CPCOMessageNegociationService;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CORoutes extends RouteBuilder {


    @ConfigProperty(name = "neli.message.co.inbox")
    String coInboxAddress;

    @ConfigProperty(name = "neli.message.cp.inbox")
    String cpInboxAddress;

    @Override
    public void configure() throws Exception {
        from(coInboxAddress)//receive data from inbox
                .log("received message ${body} from a CP")
                .unmarshal().json(CPCOMessageNegotiation.class) // convert the json to a bean
                .bean(CPCOMessageNegociationService.class) // apply business logic
                .setHeader("senderType",constant("co"))
                .marshal().json() //convert back to json
                .log("sending response message ${body} to CP")
                .to(cpInboxAddress); //send it to CP
    }
}