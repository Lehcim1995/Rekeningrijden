package interfaces;

import domain.Checkpoint;

import java.util.List;
import java.util.Map;

public interface SimulatieDao extends DaoFacade<Checkpoint, String>
{
    List<Checkpoint> getCheckpointFromCar(int carId);

    Map<Integer, List<Checkpoint>> getAllCheckpoints();

    void clearData();
}
