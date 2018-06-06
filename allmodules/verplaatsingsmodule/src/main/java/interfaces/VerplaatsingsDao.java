package interfaces;

import domain.Verplaatsing;

import java.util.Date;
import java.util.List;

public interface VerplaatsingsDao extends DaoFacade<Verplaatsing, String>
{
    /**
     *
     * @param key
     * @return
     */
    List<Verplaatsing> getVerplaatsingen(String key);

    List<Verplaatsing> getVerplaatsingen(
            String licencePlate,
            Date start,
            Date end);

    /**
     *
     * @param verplaatsing
     * @return
     */
    boolean VerplaatsingMissing(Verplaatsing verplaatsing);
}
