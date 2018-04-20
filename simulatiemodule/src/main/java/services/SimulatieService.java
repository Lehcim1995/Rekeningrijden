package services;

import classes.Checkpoint;
import classes.Verplaatsing;
import gateway.DisplacementGateway;
import interfaces.SimulatieDao;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class SimulatieService
{
    @Inject
    private SimulatieDao simulatieDao;

    private DisplacementGateway displacementGateway;

    @PostConstruct
    public void init() {
        this.displacementGateway = new DisplacementGateway();
    }


    public Checkpoint create(Checkpoint object) {
        displacementGateway.SendObject(object);
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
