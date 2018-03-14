package dao;

import classes.Verplaatsing;
import interfaces.VerplaatsingsDao;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Stateless
public class VerplaatsingsDaoCollection implements VerplaatsingsDao
{

    private Map<String, List<Verplaatsing>> carTranslations;

    @Override
    public Verplaatsing create(Verplaatsing object) {

        carTranslations.computeIfAbsent(object.getVoertuigId(), k -> new ArrayList<>());
        carTranslations.get(object.getVoertuigId())
                       .add(object);
        return object;
    }

    @Override
    public String delete(String id) {

        carTranslations.remove(id);

        return id;
    }

    @Override
    public void edit(
            Verplaatsing object,
            Verplaatsing object2)
    {
    }

    @Override
    public void edit(
            String s,
            Verplaatsing object)
    {
    }

    @Override
    public Verplaatsing get(String key) {
        return carTranslations.get(key).get(0);
    }
}
