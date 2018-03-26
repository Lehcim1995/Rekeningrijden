package classes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Owner implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private int citizenId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String address;
    private String city;

    public Owner() {}

    public Owner(int citizenId, String firstName, String middleName, String lastName, String address, String city) {
        this.citizenId = citizenId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
    }

    public Owner(int citizenId, String firstName, String lastName, String address, String city) {
        this.citizenId = citizenId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
    }
}
