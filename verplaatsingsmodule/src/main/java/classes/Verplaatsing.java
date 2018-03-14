package classes;

import javax.xml.crypto.Data;
import java.io.Serializable;
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
}
