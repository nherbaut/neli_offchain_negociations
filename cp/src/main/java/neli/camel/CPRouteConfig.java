package neli.camel;

import neli.service.CPCONegiciationService;
import neli.service.CPSPNegociationService;
import neli.model.CPCOMessageNegotiation;
import neli.model.CPSPMessageNegotiation;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CPRouteConfig extends RouteBuilder {

    @ConfigProperty(name = "neli.message.co.inbox")
    String coInboxAddress;

    @ConfigProperty(name = "neli.message.cp.inbox")
    String cpInboxAddress;

    @ConfigProperty(name = "neli.message.sp.inbox")
    String spInboxAddress;

    /* @ConfigProperty(name = "neli.message.cp.outbox")
    String finalContractOutbox; */

    @Inject
    CPCONegiciationService cpcoNegiciationService;

    @Inject
    CPSPNegociationService cpspNegociationService;

    @Override
    public void configure() throws Exception {
        //receive a message from within the application targeted at the co
        from("direct://co-in")
                .marshal().json()//convert it to json
                .setHeader("senderType", constant("cp"))
                .log("seding message ${body} to co")
                .to(coInboxAddress);//send it to co

        //receive a message from within the application targeted at the sp
        from("direct://sp-in")
                .marshal().json()//convert it to json
                .setHeader("senderType", constant("cp"))
                .log("sending message ${body} to sp")
                .to(spInboxAddress);//send it to sp

        /* from("direct://final-contract")
                .marshal().json()
                .log("writing the final contract to disk ${body}")
                .to(finalContractOutbox); */

        //receive a message in my inbox
        from(cpInboxAddress)
                .log("received in cp inbox: ${body} ${header.senderType}")
                .choice()
                //if the message is from a cp
                .when(header("senderType").isEqualTo("co"))
                .log("received Negotiation response ${body} from a co")
                //convert to object
                .unmarshal().json(CPCOMessageNegotiation.class)
                //handle
                .bean(cpcoNegiciationService)
                //if the message is from a sp
                .when(header("senderType").isEqualTo("sp"))
                .log("received Negotiation response ${body} from a sp")
                //convert to object
                .unmarshal().json(CPSPMessageNegotiation.class)
                //handle
                .bean(cpspNegociationService);


    }
}
