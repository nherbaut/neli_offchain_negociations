package neli.service;

import neli.camel.MessageGateway;
import neli.model.CPCOMessageNegotiation;
import neli.rest.client.BlockchainService;
import org.apache.camel.Handler;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
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

    @Inject
    @RestClient
    BlockchainService blockchainService;

    @Handler
    public void processMessageNegociation(CPCOMessageNegotiation cpcoMessageNegotiation) {
        //this is were the logic of the negociation between CP and CO goes
        if (cpcoMessageNegotiation.isCoAgreed() && cpcoMessageNegotiation.isCpAgreed()) {
            //both agreed, now let's move to the SP part
            cpspNegociationService.sendProposalToSP(cpcoMessageNegotiation);

            String contentID = cpcoMessageNegotiation.getContentID();
            String owner = cpcoMessageNegotiation.getOwner();
            String price = String.valueOf(cpcoMessageNegotiation.getPrice());
            Map<String, Object> jsonToSend = new HashMap<String, Object>(){{
                put("contentID", contentID);
                put("owner", owner);
                put("price", price);
            }};
            blockchainService.cpBuyAccess(jsonToSend);
        } else {
            //if co did not agree, let's increase the price
            cpcoMessageNegotiation.setPrice(cpcoMessageNegotiation.getPrice()+1.0);
            cpcoMessageNegotiation.setCpAgreed(true);
            messageGateway.sendMessageCO(cpcoMessageNegotiation);
        }

    }
}
