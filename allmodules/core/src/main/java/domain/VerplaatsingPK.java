package domain;

import java.io.Serializable;
import java.util.Objects;

public class VerplaatsingPK implements Serializable
{

    private String voertuigId;
    private long serieID;

    public VerplaatsingPK() {}

    public VerplaatsingPK(
            String voertuigId,
            long serieID)
    {
        this.voertuigId = voertuigId;
        this.serieID = serieID;
    }

    @Override
    public boolean equals(Object o) {
        VerplaatsingPK verplaatsing = (VerplaatsingPK) o;
        if (verplaatsing == null || getClass() != verplaatsing.getClass())
        {
            return false;
        }
        return this == verplaatsing || voertuigId == verplaatsing.voertuigId && serieID == verplaatsing.serieID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(voertuigId, serieID);
    }
}