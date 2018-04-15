package gateway;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.io.Serializable;

/**
 * Made by Jasper van Son
 */

public class Gateway implements MessageListener {

    private IGatewayImplementor gatewayImplementor;

    private GatewayMessageSender messageSender;
    private GatewayMessageReceiver messageReceiver;

    public Gateway(IGatewayImplementor gatewayImplementor, String URL, String senderChannel, String receiverChannel) {

        this.gatewayImplementor = gatewayImplementor;

        messageSender = new GatewayMessageSender(URL, senderChannel);
        messageReceiver = new GatewayMessageReceiver(this, URL, receiverChannel);
    }

    public void SendMessage(Serializable obj) {
        messageSender.SendMessage(obj);
    }

    @Override
    public void onMessage(Message message) {
        try {
            gatewayImplementor.ProcessReceivedObject(((ObjectMessage) message).getObject());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
