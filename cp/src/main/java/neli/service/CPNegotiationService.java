package neli.service;

import neli.camel.MessageGateway;
import neli.model.CPCOMessageNegotiation;
import neli.model.CPNegociationTrigger;
import neli.model.CPSPMessageNegotiation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the negotiation with CO and SP
 */
@ApplicationScoped
public class CPNegotiationService {

    @Inject
    MessageGateway gateway;

    public List<CPSPMessageNegotiation> getNegotiations() {
        return negotiations;
    }

    List<CPSPMessageNegotiation> negotiations = new ArrayList<>();

    /**
     * process a new content negotiation
     *
     * @param trigger
     */
    public void processNegotiationTrigger(CPNegociationTrigger trigger) {

        //do something with the trigger
        CPCOMessageNegotiation coInboundMessage = new CPCOMessageNegotiation(trigger.getContentID(), trigger.getOwner(), trigger.getPrice(), true, false);
        gateway.sendMessageCO(coInboundMessage);
    }

    public void addSuccessfulNegotiation(CPSPMessageNegotiation negotiation) {
        this.negotiations.add(negotiation);
    }
}
