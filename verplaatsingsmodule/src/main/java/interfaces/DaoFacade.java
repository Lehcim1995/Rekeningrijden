package interfaces;

public interface DaoFacade<F>
{
    /**
     *
     * @param object
     * @return
     */
    F create(F object);

    /**
     *
     * @param object
     */
    void delete(F object);

    /**
     *
     * @param id
     */
    void delete(Long id);

    /**
     *
     * @param object
     * @param object2
     */
    void edit(F object, F object2);

    /**
     *
     * @param id
     * @param object
     */
    void edit(Long id, F object);
}
