package classes;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.util.List;

public class Verplaatsing implements Serializable
{
    private List<Checkpoint> checkpoints;

    private String voertuigId;

    private long serieID;

    private Data time;

    public Verplaatsing(
            List<Checkpoint> checkpoints,
            String voertuigId,
            long serieID,
            Data time)
    {
        this.checkpoints = checkpoints;
        this.voertuigId = voertuigId;
        this.serieID = serieID;
        this.time = time;
    }

    public List<Checkpoint> getCheckpoints() {
        return checkpoints;
    }

    public String getVoertuigId() {
        return voertuigId;
    }

    public long getSerieID() {
        return serieID;
    }

    public Data getTime() {
        return time;
    }

    private double distance()
    {
        double distance= 0;

        Checkpoint curr = null;
        for (Checkpoint next: checkpoints) {
            if (curr != null) {
                // compare
                distance += calcDisBetweenPoints(curr, next);
            }
            curr = next;
        }

        return distance;
    }

    private double calcDisBetweenPoints(
            Checkpoint checkPointA,
            Checkpoint checkPointB)
    {
        double R = 6371e3; // metres
        double φ1 = Math.toRadians(checkPointA.getLat());
        double φ2 = Math.toRadians(checkPointB.getLat());
        double Δφ = Math.toRadians(checkPointB.getLat() - checkPointA.getLat());
        double Δλ = Math.toRadians(checkPointB.getLon() - checkPointA.getLon());

        double a = Math.sin(Δφ / 2) * Math.sin(Δφ / 2) + Math.cos(φ1) * Math.cos(φ2) * Math.sin(Δλ / 2) * Math.sin(Δλ / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double d = R * c;

        return d;
    }
}
