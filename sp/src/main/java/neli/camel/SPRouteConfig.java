package neli.camel;


import neli.model.CPSPMessageNegotiation;
import neli.service.CPSPMessageNegociationService;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class SPRouteConfig extends RouteBuilder {

    @ConfigProperty(name = "neli.message.cp.inbox")
    String cpInboxAddress;

    @ConfigProperty(name = "neli.message.sp.inbox")
    String spInboxAddress;

    @Inject
    CPSPMessageNegociationService cpspMessageNegociationService;

    @Override
    public void configure() throws Exception {


        from(spInboxAddress)
                .log("received message ${body} from ${header.senderType}")
                .unmarshal()
                .json(CPSPMessageNegotiation.class)
                .bean(cpspMessageNegociationService)
                .marshal().json()
                .setHeader("senderType", constant("sp"))
                .to(cpInboxAddress);


    }
}
