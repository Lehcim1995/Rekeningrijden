package gateway;

import com.google.gson.Gson;
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
    final private Gson gson = new Gson();

    public GatewayMessageSender(String URL, String channelName) throws NoPublicActiveMQConnectionException {
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
            throw new NoPublicActiveMQConnectionException();
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
            String json = gson.toJson(obj);
            //ObjectMessage message = session.createObjectMessage(obj);
            TextMessage message = session.createTextMessage(json);
            producer.send(message);
            System.out.println("Sent: " + obj.toString());
        }
        catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
