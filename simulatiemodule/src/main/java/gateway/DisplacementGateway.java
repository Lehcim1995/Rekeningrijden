package gateway;

import java.io.Serializable;

public class DisplacementGateway implements IGatewayImplementor {

    private Gateway gateway;
    private final String senderChannel = "DISPLACEMENTSYSTEM_DISPLACEMENTREGISTRATOR";
    private final String receiverChannel = "";

    public DisplacementGateway() {
        gateway = new Gateway(this, senderChannel, receiverChannel);
    }

    @Override
    public void SendObject(Serializable obj) {
        gateway.SendMessage(obj);
    }

    @Override
    public void ProcessReceivedObject(Object obj) {
        // Nothing TODO
    }
}
