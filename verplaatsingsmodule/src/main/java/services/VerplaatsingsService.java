package services;

import classes.Verplaatsing;
import interfaces.VerplaatsingsDao;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class VerplaatsingsService
{
    @Inject
    private VerplaatsingsDao verplaatsingsDao;

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
