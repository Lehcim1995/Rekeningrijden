package rest.JsonBodyClasses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RegisterModel implements Serializable {

    private String email;
    private int bsn;

    public RegisterModel() {}

    public String getEmail() {
        return email;
    }

    public int getBsn() {
        return bsn;
    }
}
