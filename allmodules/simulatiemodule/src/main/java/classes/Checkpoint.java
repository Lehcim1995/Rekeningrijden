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
   private  long id;

   private  double lon;
   private  double lat;

   private  Date time;

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
        this.lon = stepLon;
        this.lat = stepLat;
        this.time = new Date();
    }

    public long getID() { return id; }

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
