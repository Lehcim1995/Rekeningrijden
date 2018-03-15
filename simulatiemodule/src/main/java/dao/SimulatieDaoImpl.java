package dao;

import classes.Checkpoint;
import classes.Verplaatsing;
import interfaces.SimulatieDao;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.Map;

@Stateless
public class SimulatieDaoImpl implements SimulatieDao
{

    Map<String, Checkpoint> checkpointMap;

    @PostConstruct
    public void init()
    {
        checkpointMap = new HashMap<>();
    }

    @Override
    public Checkpoint create(Checkpoint object) {
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
