package neli.service;

import neli.model.CPSPMessageNegotiation;
import org.apache.camel.Handler;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;

@ApplicationScoped
public class CPSPMessageNegociationService {

    @Handler
    public CPSPMessageNegotiation handle(CPSPMessageNegotiation cpspMessageNegociation) {
        //this is where the negotiation logic on the SP side takes place
        //not its random with p=0.5 of agreement
        Random rand = new Random();
        if (rand.nextBoolean()) {
            cpspMessageNegociation.setSpAgreed(true);
            return cpspMessageNegociation;
        }
        return cpspMessageNegociation;
    }
}
