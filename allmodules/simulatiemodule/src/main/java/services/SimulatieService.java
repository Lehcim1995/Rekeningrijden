package services;

import domain.Checkpoint;
import domain.Verplaatsing;
import gateway.DisplacementGateway;
import interfaces.SimulatieDao;
import org.hibernate.annotations.Check;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

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


    public Checkpoint create(Checkpoint object, String id) {

        Checkpoint checkpoint = simulatieDao.create(object);

//        Verplaatsing verplaatsing = new Verplaatsing(Arrays.asList(checkpoint), id, checkpoint.getId(), new Date());
//
//        displacementGateway.SendObject(verplaatsing);
        return checkpoint;
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

    public Checkpoint create(Checkpoint cp) {
        Checkpoint checkpoint = simulatieDao.create(cp);

//        Verplaatsing verplaatsing = new Verplaatsing(Arrays.asList(checkpoint), id, checkpoint.getId(), new Date());
//
//        displacementGateway.SendObject(verplaatsing);
        return checkpoint;
    }

    public List<Checkpoint> getCheckpointFromCar(int carId) {
        return simulatieDao.getCheckpointFromCar(carId);
    }

    public Map<Integer, List<Checkpoint>> getAll() {
        return simulatieDao.getAllCheckpoints();
    }

    public void clearData() {
        simulatieDao.clearData();
    }
}
