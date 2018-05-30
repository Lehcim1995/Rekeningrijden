package dao;

import domain.Checkpoint;
import interfaces.SimulatieDao;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
public class SimulatieDaoImpl implements SimulatieDao
{

    Map<Integer, List<Checkpoint>> checkpointMap;

    @PersistenceContext(unitName = "simPU")
    private EntityManager em;

    @PostConstruct
    public void init()
    {
        checkpointMap = new HashMap<>();
    }

    @Override
    public Checkpoint create(Checkpoint object) {
//        em.persist(object);

        if (!checkpointMap.containsKey(object.getCarId()))
        {
            checkpointMap.put(object.getCarId(), new ArrayList<>());
        }

        checkpointMap.get(object.getCarId()).add(object);

        System.out.println("adding checkpoint with carid " + object.getCarId());
        System.out.println("adding checkpoint with id " + object.getId());

        return object;
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

    @Override
    public List<Checkpoint> getCheckpointFromCar(int carId)
    {
        return checkpointMap.get(carId);
    }

    @Override
    public Map<Integer, List<Checkpoint>> getAllCheckpoints()
    {
        return checkpointMap;
    }

    @Override
    public void clearData()
    {
        checkpointMap.clear();
    }
}
