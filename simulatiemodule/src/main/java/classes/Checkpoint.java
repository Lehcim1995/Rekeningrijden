package classes;

import java.io.Serializable;
import java.util.Date;

public class Checkpoint implements Serializable
{
    double lon;
    double lat;

    Date time;

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
