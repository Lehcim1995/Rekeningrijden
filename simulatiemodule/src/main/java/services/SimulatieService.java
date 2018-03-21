package services;

import classes.Checkpoint;
import classes.Verplaatsing;
import interfaces.SimulatieDao;

import javax.inject.Inject;

public class SimulatieService
{
    @Inject
    private SimulatieDao simulatieDao;

    public Checkpoint create(Checkpoint object) {
        return simulatieDao.create(object);
    }

    public String delete(String s) {
        return simulatieDao.delete(s);
    }

    public Checkpoint get(String s) {
        return simulatieDao.get(s);
    }

    public void edit(
            Checkpoint object,
            Checkpoint object2)
    {
        simulatieDao.edit(object, object2);
    }

    public void edit(
            String s,
            Checkpoint object)
    {
        simulatieDao.edit(s, object);
    }
}
