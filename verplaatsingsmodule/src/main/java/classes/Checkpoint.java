package classes;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.Date;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Checkpoint implements Serializable
{
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
