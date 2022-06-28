package neli.service;

import neli.camel.MessageGateway;
import neli.model.CPCOMessageNegotiation;
import org.apache.camel.Handler;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Random;

/**
 * handles the negotiation between CP CO
 */
@ApplicationScoped
public class CPCONegiciationService {

    @Inject
    MessageGateway messageGateway;

    @Inject
    CPSPNegociationService cpspNegociationService;

    @Handler
    public void processMessageNegociation(CPCOMessageNegotiation cpcoMessageNegotiation) {
        //this is were the logic of the negociation between CP and CO goes
        Random rand = new Random();
        if (cpcoMessageNegotiation.isCoAgreed() && cpcoMessageNegotiation.isCpAgreed()) {
            //both agreed, now let's move to the SP part
            cpspNegociationService.sendProposalToSP(cpcoMessageNegotiation);

        } else {
            //if co did not agree, let's increase the price
            cpcoMessageNegotiation.setPrice(cpcoMessageNegotiation.getPrice()+1.0);
            cpcoMessageNegotiation.setCpAgreed(true);
            messageGateway.sendMessageCO(cpcoMessageNegotiation);
        }

    }
}
