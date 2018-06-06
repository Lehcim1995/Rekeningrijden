package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RegisteredModel implements Serializable {

    private String email;
    private String password;
    private int bsn;

    public RegisteredModel() {}

    public RegisteredModel(String email, String password, int bsn) {
        this.email = email;
        this.password = password;
        this.bsn = bsn;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getBsn() {
        return bsn;
    }
}
