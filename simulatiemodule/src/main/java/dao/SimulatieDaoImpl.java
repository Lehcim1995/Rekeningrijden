package dao;

import classes.Checkpoint;
import classes.Verplaatsing;
import interfaces.SimulatieDao;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.Map;

@Stateless
public class SimulatieDaoImpl implements SimulatieDao
{

    Map<String, Checkpoint> checkpointMap;

    @PersistenceContext(unitName = "simPU")
    private EntityManager em;

    @PostConstruct
    public void init()
    {
        checkpointMap = new HashMap<>();
    }

    @Override
    public Checkpoint create(Checkpoint object) {
        em.persist(object);
        return checkpointMap.put(checkpointMap.size() + "", object);
    }

    @Override
    public String delete(String s) {
        return null;
    }

    @Override
    public Checkpoint get(String s) {
        return null;
    }

    @Override
    public void edit(
            Checkpoint object,
            Checkpoint object2)
    {

    }

    @Override
    public void edit(
            String s,
            Checkpoint object)
    {

    }
}
