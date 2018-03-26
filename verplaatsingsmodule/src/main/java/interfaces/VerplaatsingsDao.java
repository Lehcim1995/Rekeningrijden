package interfaces;

import classes.Verplaatsing;

import java.util.List;

public interface VerplaatsingsDao extends DaoFacade<Verplaatsing, String>
{
    /**
     *
     * @param key
     * @return
     */
    List<Verplaatsing> getVerplaatsingen(String key);

    /**
     *
     * @param verplaatsing
     * @return
     */
    boolean CheckVerplaatsingMissing(Verplaatsing verplaatsing);
}
