package gateway;

import classes.Verplaatsing;
import services.VerplaatsingsService;

import javax.inject.Inject;
import java.io.Serializable;

public class DisplacementReceiverGateway implements IGatewayImplementor {

    private Gateway gateway;
    private final String senderChannel = "";
    private final String receiverChannel = "DISPLACEMENTSYSTEM_DISPLACEMENTREGISTRATOR";

    @Inject
    private VerplaatsingsService service;

    public DisplacementReceiverGateway() {
        gateway = new Gateway(this, senderChannel, receiverChannel);
    }

    @Override
    public void SendObject(Serializable obj) {
        gateway.SendMessage(obj);
    }

    @Override
    public void ProcessReceivedObject(Object obj) {
        if (obj instanceof Verplaatsing) service.create((Verplaatsing) obj);
    }
}