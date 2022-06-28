package neli.service;

import neli.camel.MessageGateway;
import neli.model.CPCOMessageNegotiation;
import neli.model.CPSPMessageNegotiation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CPSPNegociationService {


    @Inject
    MessageGateway messageGateway;

    /**
     * send a proposal to the SP containing the previously negiciated contract with the CO
     *
     * @param cpcoMessageNegociation the result of the co negociation
     */
    public void sendProposalToSP(CPCOMessageNegotiation cpcoMessageNegociation) {
        sendProposalToSP(cpcoMessageNegociation, 10);
    }

    public void sendProposalToSP(CPCOMessageNegotiation cpcoMessageNegociation, double price) {
        CPSPMessageNegotiation cpspMessageNegociation = new CPSPMessageNegotiation(cpcoMessageNegociation.getContentId(), price);
        cpspMessageNegociation.setCpAgreed(true);
        cpspMessageNegociation.setCpcoMessageNegotiation(cpcoMessageNegociation);
        messageGateway.sendMessageSP(cpspMessageNegociation);
    }

    public void handleResponseFromSP(CPSPMessageNegotiation cpspMessageNegotiation) {
        //this is where we handle the response from the SP.
        //for the moment, if the agreement is declined, we just increase the price and resubmit
        if (cpspMessageNegotiation.isSpAgreed() && cpspMessageNegotiation.isCpAgreed()) {
            messageGateway.writeTripleContract(cpspMessageNegotiation);
        } else {
            this.sendProposalToSP(cpspMessageNegotiation.getCpcoMessageNegotiation(), cpspMessageNegotiation.getPrice() + 1.0);
        }
    }


}
