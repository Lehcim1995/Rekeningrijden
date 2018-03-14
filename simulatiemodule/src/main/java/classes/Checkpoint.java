package classes;

import java.util.Date;

public class Checkpoint
{
    long Long;
    long lat;

    Date time;

    public Checkpoint(
            long lon,
            long lat,
            Date time)
    {
        Long = lon;
        this.lat = lat;
        this.time = time;
    }

    public Checkpoint(
            double stepLon,
            double stepLat)
    {

    }
}
