package dao;

import classes.Verplaatsing;
import interfaces.VerplaatsingsDao;

import java.util.List;

public class VerplaatsingsDaoCollection implements VerplaatsingsDao
{
    List<Verplaatsing> verplaatsings;

    @Override
    public Verplaatsing create(Verplaatsing object) {
        return null;
    }

    @Override
    public void delete(Verplaatsing object) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void edit(
            Verplaatsing object,
            Verplaatsing object2)
    {

    }

    @Override
    public void edit(
            Long id,
            Verplaatsing object)
    {

    }
}
