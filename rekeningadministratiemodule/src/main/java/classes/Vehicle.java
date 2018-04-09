package classes;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.Date;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Vehicle implements Serializable {

    @Id
    private String vehicleTrackerID;
    private String rateCategorie;
    private String licensePlate;
    private Date buildYear;
    @ManyToOne
    private Owner owner;

    public Vehicle() {}

}
