package gateway;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Made by Jasper van Son
 */

public class GatewayMessageReceiver {

    public GatewayMessageReceiver(MessageListener listener, String URL, String channelName) throws NoPublicActiveMQConnectionException {
        try {
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(URL);
            factory.setTrustAllPackages(true);
            Connection connection = factory.createConnection();
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(channelName);
            MessageConsumer consumer = session.createConsumer(destination);
            consumer.setMessageListener(listener);
        }
        catch (JMSException e) {
            e.printStackTrace();
            throw new NoPublicActiveMQConnectionException();
        }
    }
}