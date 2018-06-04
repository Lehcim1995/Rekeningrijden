package services;

import domain.Verplaatsing;
import gateway.DisplacementReceiverGateway;
import interfaces.JPA;
import interfaces.VerplaatsingsDao;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Default
public class VerplaatsingsService
{
    @Inject
    @JPA
    private VerplaatsingsDao verplaatsingsDao;

    private DisplacementReceiverGateway gateway;

    @PostConstruct
    public void init(){}

    public void setVerplaatsingsDao(VerplaatsingsDao verplaatsingsDao)
    {
        this.verplaatsingsDao = verplaatsingsDao;
    }

    public Verplaatsing create(Verplaatsing object) {
        return verplaatsingsDao.create(object);
    }

    public String delete(String s) {
        return verplaatsingsDao.delete(s);
    }

    public List<Verplaatsing> getVerplaatsingsForVehicle(String licence)
    {

        return verplaatsingsDao.getVerplaatsingen(licence);
    }
}
