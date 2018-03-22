package classes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Checkpoint implements Serializable
{
    @Id
    @GeneratedValue
    long id;

    double lon;
    double lat;

    Date time;

    public Checkpoint() {
    }

    public Checkpoint(
            double lon,
            double lat,
            Date time)
    {
        this.lon = lon;
        this.lat = lat;
        this.time = time;
    }

    public Checkpoint(
            double stepLon,
            double stepLat)
    {

    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }

    public Date getTime() {
        return time;
    }
}
