package classes;

import javax.xml.crypto.Data;
import java.util.List;

public class Verplaatsing
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
}
