package services;

import classes.Verplaatsing;
import gateway.DisplacementReceiverGateway;
import interfaces.JPA;
import interfaces.VerplaatsingsDao;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Stateless
@Default
public class VerplaatsingsService
{
    @Inject
    @JPA
    private VerplaatsingsDao verplaatsingsDao;

    private DisplacementReceiverGateway gateway;

    @PostConstruct
    public void init(){};

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
}
