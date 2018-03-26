package classes;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.Date;

@Entity
@IdClass(VerplaatsingPK.class)
@XmlAccessorType(XmlAccessType.FIELD)
public class MissedVerplaatsing implements Serializable {

    @Id
    private String voertuigId;
    @Id
    private long serieID;

    private Date date;

    public MissedVerplaatsing() {}

    public MissedVerplaatsing(String voertuigId, long serieID) {
        this.voertuigId = voertuigId;
        this.serieID = serieID;
        this.date = new Date();
    }

    public String getVoertuigId() {
        return voertuigId;
    }

    public long getSerieID() {
        return serieID;
    }

    public Date getDate() {
        return date;
    }
}
