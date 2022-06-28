package neli.camel;

import neli.model.CPCOMessageNegotiation;
import neli.model.CPSPMessageNegotiation;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.ExchangeBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * this class wraps the messaging layer from the application
 */
@ApplicationScoped
public class MessageGateway {

    @Inject
    ProducerTemplate template;


    @Inject
    CamelContext context;

    /**
     * send a message to the co through direct endpoint
     *
     * @param message
     */
    public void sendMessageCO(CPCOMessageNegotiation message) {
        Exchange exchange = ExchangeBuilder.anExchange(context).withBody(message).build();
        template.send("direct://co-in", exchange);

    }

    /**
     * send a message to the sp through direct endpoint
     *
     * @param message
     */
    public void sendMessageSP(CPSPMessageNegotiation message) {
        Exchange exchange = ExchangeBuilder.anExchange(context).withBody(message).build();
        template.send("direct://sp-in", exchange);

    }

    public void writeTripleContract(CPSPMessageNegotiation cpspMessageNegotiation) {
        Exchange exchange = ExchangeBuilder.anExchange(context).withBody(cpspMessageNegotiation).build();
        template.send("direct://final-contract", exchange);
    }
}
