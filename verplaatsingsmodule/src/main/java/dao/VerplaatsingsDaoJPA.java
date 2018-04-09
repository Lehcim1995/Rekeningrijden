package dao;

import classes.MissedVerplaatsing;
import classes.Verplaatsing;
import interfaces.JPA;
import interfaces.VerplaatsingsDao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
@JPA
public class VerplaatsingsDaoJPA implements VerplaatsingsDao {

    @PersistenceContext(unitName = "kwetterPU")
    private EntityManager em;
    private CriteriaBuilder cb;
    private CriteriaQuery<Verplaatsing> cp;
    private Root<Verplaatsing> verplaatsingRoot;

    @Override
    public Verplaatsing create(Verplaatsing verplaatsing) {
        
        verplaatsing = new Verplaatsing(verplaatsing);

        if (VerplaatsingMissing(verplaatsing)) LogMissedVerplaatsingen(verplaatsing);

        em.persist(verplaatsing);

        return verplaatsing;
    }

    @Override
    public String delete(String id) {

        em.remove(id);

        return id;
    }

    @Override
    public void edit(
            Verplaatsing object,
            Verplaatsing object2)
    {
    }

    @Override
    public void edit(
            String s,
            Verplaatsing object)
    {
    }

    @Override
    public Verplaatsing get(String key) {
        return getVerplaatsingen(key).get(0);
    }

    /**
     * @param key
     * @return
     */
    @Override
    public List<Verplaatsing> getVerplaatsingen(String key) {
        setupJPA();
        return em.createQuery(cp.where(cb.equal(verplaatsingRoot.get("voertuigId"), key))).getResultList();
    }

    /**
     * @param verplaatsing
     * @return
     */
    @Override
    public boolean VerplaatsingMissing(Verplaatsing verplaatsing) {

        List<Verplaatsing> verplaatsingen = getVerplaatsingen(verplaatsing.getVoertuigId());

        if (verplaatsingen.isEmpty()) return verplaatsing.getSerieID() == 0 ? true : false;
        else return verplaatsingen.get(verplaatsingen.size() - 1).getSerieID() == verplaatsing.getSerieID() - 1 ? true : false;
    }

    public void LogMissedVerplaatsingen(Verplaatsing verplaatsing) {
        List<Verplaatsing> verplaatsingen = getVerplaatsingen(verplaatsing.getVoertuigId());

        for (long i = verplaatsingen.get(verplaatsingen.size() - 1).getSerieID(); i < verplaatsing.getSerieID(); i++) {
            em.persist(new MissedVerplaatsing(verplaatsing.getVoertuigId(), i));
        }
    }

    public void setupJPA() {
        cp = cb.createQuery(Verplaatsing.class);
        verplaatsingRoot = cp.from(Verplaatsing.class);
        cp.select(verplaatsingRoot);
    }
}
