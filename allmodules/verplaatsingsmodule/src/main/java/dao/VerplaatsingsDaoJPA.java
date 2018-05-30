package dao;

import classes.MissedVerplaatsing;
import domain.Checkpoint;
import domain.Verplaatsing;
import interfaces.JPA;
import interfaces.VerplaatsingsDao;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
@JPA
public class VerplaatsingsDaoJPA implements VerplaatsingsDao
{

    @PersistenceContext(unitName = "displacementPU")
    private EntityManager em;
    private CriteriaBuilder cb;
    private CriteriaQuery<Verplaatsing> cp;
    private CriteriaQuery<Checkpoint> cpcp;
    private Root<Verplaatsing> verplaatsingRoot;
    private Root<Checkpoint> checkpointRoot;

    @PostConstruct
    public void init() {
        System.out.print("Initializing profiles query");

        cb = em.getCriteriaBuilder();
        setupJPA();
        setupCheckpointJPA();
    }

    @Override
    public Verplaatsing create(Verplaatsing verplaatsing) {

//        List<Checkpoint> checkpoints = new ArrayList<>();

//        for (Checkpoint checkpoint : verplaatsing.getCheckpoints()) {
//            em.persist(checkpoint);
//            checkpoints.add(checkpoint);
//        }
//
//        verplaatsing.setCheckpoints(checkpoints);

//        TODO fix
//        if (VerplaatsingMissing(verplaatsing)) {
//            LogMissedVerplaatsingen(verplaatsing);
//        }

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
//        setupJPA();
//        return em.createQuery(cp.where(cb.equal(verplaatsingRoot.get("voertuigId"), key))).getResultList();
        return em.createQuery("SELECT v FROM Verplaatsing v WHERE v.voertuigId = :key", Verplaatsing.class)
                 .setParameter("key", key)
                 .getResultList();
    }

    /**
     * @param verplaatsing
     * @return
     */
    @Override
    public boolean VerplaatsingMissing(Verplaatsing verplaatsing) {

        List<Verplaatsing> verplaatsingen = getVerplaatsingen(verplaatsing.getVoertuigId());

        if (verplaatsingen.isEmpty())
        {
            return false;
        }

        return verplaatsingen.get(verplaatsingen.size() - 1)
                             .getSerieID() + 1 == verplaatsing.getSerieID();
    }

    private void LogMissedVerplaatsingen(Verplaatsing verplaatsing) {
        List<Verplaatsing> verplaatsingen = getVerplaatsingen(verplaatsing.getVoertuigId());

        for (long i = verplaatsingen.get(verplaatsingen.size() - 1)
                                    .getSerieID(); i < verplaatsing.getSerieID(); i++)
        {
            em.persist(new MissedVerplaatsing(verplaatsing.getVoertuigId(), i));
        }
    }

    private void setupJPA() {
        cp = cb.createQuery(Verplaatsing.class);
        verplaatsingRoot = cp.from(Verplaatsing.class);
        cp.select(verplaatsingRoot);
    }

    private void setupCheckpointJPA() {
        cpcp = cb.createQuery(Checkpoint.class);
        checkpointRoot = cpcp.from(Checkpoint.class);
        cpcp.select(checkpointRoot);
    }
}
