package dao;

import classes.Checkpoint;
import classes.Verplaatsing;
import interfaces.SimulatieDao;

import javax.ejb.Stateless;
import java.util.Map;

@Stateless
public class SimulatieDaoImpl implements SimulatieDao
{

    Map<String, Checkpoint> checkpointMap;

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
