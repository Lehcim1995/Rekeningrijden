package gateway;

import javax.jms.Message;
import java.io.Serializable;

public interface IGatewayImplementor {

    void SendObject(Serializable obj);

    void ProcessReceivedObject(Message message);
}
