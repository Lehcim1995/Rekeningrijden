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

        boolean verplaatsingGemist = CheckVerplaatsingMissing(object);

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

    /**
     * @param key
     * @return
     */
    @Override
    public List<Verplaatsing> getVerplaatsingen(String key) {
        return carTranslations.get(key);
    }

    /**
     * @param verplaatsing
     * @return
     */
    @Override
    public boolean CheckVerplaatsingMissing(Verplaatsing verplaatsing) {

        List<Verplaatsing> verplaatsingen = getVerplaatsingen(verplaatsing.getVoertuigId());

        if (verplaatsingen.isEmpty()) return verplaatsing.getSerieID() == 0 ? true : false;
        else return verplaatsingen.get(verplaatsingen.size() - 1).getSerieID() == verplaatsing.getSerieID() - 1 ? true : false;
    }
}
