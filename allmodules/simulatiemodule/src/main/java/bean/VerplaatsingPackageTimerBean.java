package bean;

import domain.Checkpoint;
import domain.Verplaatsing;
import gateway.DisplacementGateway;
import services.SimulatieService;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class VerplaatsingPackageTimerBean
{
    private static final int packegeSize = 10;

    private DisplacementGateway displacementGateway;

    @Inject
    SimulatieService simulatieService;

    @PostConstruct
    public void init() {
        this.displacementGateway = new DisplacementGateway();
    }


    @Schedule(minute = "*/1", hour = "*", persistent = false)
    private void sendVerplaatsing()
    {
        System.out.println("Sending shizzel");

        for (Map.Entry<Integer, List<Checkpoint>> entry : simulatieService.getAll()
                                                                          .entrySet())
        {
            Verplaatsing v = new Verplaatsing(entry.getValue(), entry.getKey() + "", 0, new Date());

            displacementGateway.SendObject(v);
        }

        simulatieService.clearData();
    }
}
