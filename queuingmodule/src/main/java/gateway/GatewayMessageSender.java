package gateway;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.Serializable;

/**
 * Made by Jasper van Son
 */

public class GatewayMessageSender {

    private Session session = null;
    private Destination destination = null;
    private MessageProducer producer = null;

    public GatewayMessageSender(String URL, String channelName) {
        try {
            ConnectionFactory factory = new ActiveMQConnectionFactory(URL);
            Connection connection = factory.createConnection();
            connection.start();

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue(channelName);
            producer = session.createProducer(destination);
        }
        catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void setDestination(String channelName) {
        try {
            destination = session.createQueue(channelName);
            producer = session.createProducer(destination);
        }
        catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void SendMessage(Serializable obj) {
        try {
            ObjectMessage message = session.createObjectMessage(obj);
            producer.send(message);
            System.out.println("Sent: " + obj.toString());
        }
        catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
