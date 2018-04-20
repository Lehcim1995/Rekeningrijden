package classes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.Date;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Checkpoint implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    long Long;
    long lat;

    Date time;

    public Checkpoint() {}

    public Checkpoint(
            long aLong,
            long lat,
            Date time)
    {
        Long = aLong;
        this.lat = lat;
        this.time = time;
    }
}
