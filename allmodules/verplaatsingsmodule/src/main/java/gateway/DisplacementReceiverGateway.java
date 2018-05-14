package gateway;

import classes.Verplaatsing;
import com.google.gson.Gson;
import interfaces.JPA;
import services.VerplaatsingsService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.io.Serializable;

@Singleton
@Startup
public class DisplacementReceiverGateway implements IGatewayImplementor {

    private Gateway gateway;
    private final String senderChannel = "";
    private final String receiverChannel = "DISPLACEMENTSYSTEM_DISPLACEMENTREGISTRATOR";

    private final Gson gson = new Gson();

    @Inject
    private VerplaatsingsService service;

    public void setService(VerplaatsingsService service) {
        this.service = service;
    }

    @PostConstruct
    public void init() {
        gateway = new Gateway(this, senderChannel, receiverChannel);
    }

//    public DisplacementReceiverGateway() {
//        gateway = new Gateway(this, senderChannel, receiverChannel);
//    }

    @Override
    public void SendObject(Serializable obj) {
        gateway.SendMessage(obj);
    }

    @Override
    public void ProcessReceivedObject(Message message) {
        try {
            Verplaatsing verplaatsing = gson.fromJson(((TextMessage) message).getText(), Verplaatsing.class);
            //Verplaatsing verplaatsing = (Verplaatsing) ((ObjectMessage) message).getObject();
            service.create(verplaatsing);
        } catch (JMSException e) {
            e.printStackTrace();
        }
        catch (NullPointerException npe)
        {
            System.out.println("is null mogol");
        }
    }
}