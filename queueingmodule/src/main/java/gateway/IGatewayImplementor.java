package gateway;

import java.io.Serializable;

public interface IGatewayImplementor {

    void SendObject(Serializable obj);

    void ProcessReceivedObject(Object obj);
}
