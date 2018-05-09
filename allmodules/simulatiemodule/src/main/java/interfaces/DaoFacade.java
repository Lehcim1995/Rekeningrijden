package interfaces;

import java.io.Serializable;

/**
 *
 * @param <F> The Doa object
 * @param <ID> The key type for the object
 */
public interface DaoFacade<F extends Serializable, ID>
{
    /**
     *
     * @param object
     * @return
     */
    F create(F object);

    /**
     *
     * @param id
     */
    ID delete(ID id);

    /**
     *
     * @param id
     * @return
     */
    F get(ID id);

    /**
     *
     * @param object
     * @param object2
     */
    void edit(
            F object,
            F object2);

    /**
     *
     * @param id
     * @param object
     */
    void edit(
            ID id,
            F object);
}
