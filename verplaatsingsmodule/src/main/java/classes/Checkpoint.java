package classes;

import java.util.Date;

public class Checkpoint
{
    long Long;
    long lat;

    Date time;

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
