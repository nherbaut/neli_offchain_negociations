package neli.service;

import neli.camel.MessageGateway;
import neli.model.CPCOMessageNegotiation;
import neli.model.CPNegociationTrigger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Handles the negotiation with CO and SP
 */
@ApplicationScoped
public class CPInitiateNegotiationService {

    @Inject
    MessageGateway gateway;

    /**
     * process a new content negotiation
     * @param trigger
     */
    public void processNegotiationTrigger(CPNegociationTrigger trigger) {

        //do something with the trigger
        CPCOMessageNegotiation coInboundMessage = new CPCOMessageNegotiation(trigger.getContentId(), trigger.getPrice(), true, false);
        gateway.sendMessageCO(coInboundMessage);

    }
}
