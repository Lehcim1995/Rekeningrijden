package domain;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@IdClass(VerplaatsingPK.class)
@XmlAccessorType(XmlAccessType.FIELD)
public class Verplaatsing implements Serializable
{
    @Id
    private String voertuigId;
    @Id
    private long serieID;

    private Date time;

    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Checkpoint> checkpoints;

    public Verplaatsing() {}

    public Verplaatsing(Verplaatsing verplaatsing) {
        this.voertuigId = verplaatsing.getVoertuigId();
        this.serieID = verplaatsing.getSerieID();
        this.checkpoints = verplaatsing.getCheckpoints();
        this.time = verplaatsing.getTime();
    }

    public Verplaatsing(List<Checkpoint> checkpoints, String voertuigId, long serieID, Date time)
    {
        this.voertuigId = voertuigId;
        this.serieID = serieID;
        this.checkpoints = checkpoints;
        this.time = time;
    }

    public String getVoertuigId() {
        return voertuigId;
    }

    public long getSerieID() {
        return serieID;
    }

    public Date getTime() {
        return time;
    }

    public List<Checkpoint> getCheckpoints() {
        return checkpoints;
    }

    public void setCheckpoints(List<Checkpoint> checkpoints) {
        this.checkpoints = checkpoints;
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
