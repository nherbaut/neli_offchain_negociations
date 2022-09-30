package neli.service;

import neli.camel.MessageGateway;
import neli.model.CPCOMessageNegotiation;
import neli.model.CPSPMessageNegotiation;
import neli.rest.client.BlockchainService;
import org.apache.camel.model.Block;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class CPSPNegociationService {


    @Inject
    MessageGateway messageGateway;

    @Inject
    @RestClient
    BlockchainService blockchainService;

    @Inject
    CPNegotiationService cpNegotiationService;

    /**
     * send a proposal to the SP containing the previously negiciated contract with the CO
     *
     * @param cpcoMessageNegociation the result of the co negociation
     */
    public void sendProposalToSP(CPCOMessageNegotiation cpcoMessageNegociation) {
        sendProposalToSP(cpcoMessageNegociation, 10);
    }

    public void sendProposalToSP(CPCOMessageNegotiation cpcoMessageNegociation, int price) {
        CPSPMessageNegotiation cpspMessageNegociation = new CPSPMessageNegotiation(cpcoMessageNegociation.getContentID(), cpcoMessageNegociation.getOwner(), price);
        cpspMessageNegociation.setCpAgreed(true);
        cpspMessageNegociation.setCpcoMessageNegotiation(cpcoMessageNegociation);
        messageGateway.sendMessageSP(cpspMessageNegociation);
    }

    public void handleResponseFromSP(CPSPMessageNegotiation cpspMessageNegotiation) {
        //this is where we handle the response from the SP.
        //for the moment, if the agreement is declined, we just increase the price and resubmit
        if (cpspMessageNegotiation.isSpAgreed() && cpspMessageNegotiation.isCpAgreed()) {
            cpNegotiationService.addSuccessfulNegotiation(cpspMessageNegotiation);

            String contentID = cpspMessageNegotiation.getContentID();
            String price = String.valueOf(cpspMessageNegotiation.getPrice());
            Map<String, Object> jsonToSend = new HashMap<String, Object>(){{
                put("contentID", contentID);
                put("price", price);
            }};
            blockchainService.cpBuyService(jsonToSend);
        } else {
            this.sendProposalToSP(cpspMessageNegotiation.getCpcoMessageNegotiation(), cpspMessageNegotiation.getPrice() + 1);
        }
    }


}
