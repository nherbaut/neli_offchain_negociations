package neli.service;

import neli.model.CPCOMessageNegotiation;
import org.apache.camel.Handler;

import java.util.Random;

public class CPCOMessageNegociationService {
    @Handler
    CPCOMessageNegotiation processCOInboundMessage(CPCOMessageNegotiation coInboundMessage) {
        //this is where we have the logic for the negociation between CP and CO
        //currently it's random with p=0.5
        Random random = new Random();
        if (random.nextBoolean()) {
            coInboundMessage.setCoAgreed(true);
        }
        return coInboundMessage;

    }
}
