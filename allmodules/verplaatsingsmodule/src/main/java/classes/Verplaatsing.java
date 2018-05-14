package classes;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
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
}
