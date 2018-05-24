package gateway;

import com.google.gson.Gson;

import javax.jms.Message;
import javax.jms.MessageListener;
import java.io.Serializable;

/**
 * Made by Jasper van Son
 */

public class Gateway implements MessageListener {

    private IGatewayImplementor gatewayImplementor;

    private GatewayMessageSender messageSender;
    private GatewayMessageReceiver messageReceiver;

    private final Gson gson = new Gson();

    // Localhost ActiveMQ URL
    final private String localURL = "tcp://localhost:61616";
    // TODO server ActiveMQ URL autoselect
    final private String URL = "tcp://192.168.25.137:61616";

    public Gateway(IGatewayImplementor gatewayImplementor, String senderChannel, String receiverChannel) {

        this.gatewayImplementor = gatewayImplementor;

        try {
            if (!senderChannel.isEmpty()) messageSender = new GatewayMessageSender(URL, senderChannel);
            if (!receiverChannel.isEmpty()) messageReceiver = new GatewayMessageReceiver(this, URL, receiverChannel);
        } catch (NoPublicActiveMQConnectionException ex) {
            try {
                if (!senderChannel.isEmpty()) messageSender = new GatewayMessageSender(localURL, senderChannel);
                if (!receiverChannel.isEmpty()) messageReceiver = new GatewayMessageReceiver(this, localURL, receiverChannel);
            } catch (NoPublicActiveMQConnectionException fex) {
                fex.printStackTrace();
            }
        }
    }

    public void SendMessage(Serializable obj) {
        messageSender.SendMessage(obj);
    }

    @Override
    public void onMessage(Message message) {
        gatewayImplementor.ProcessReceivedObject(message);
    }
}
