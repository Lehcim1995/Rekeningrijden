package dao;

import domain.KilometerRate;
import domain.RateCategory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

public class RateDaoJPA implements RateDao{

    @PersistenceContext(name = "accountAdministrationPU")
    private EntityManager em;

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public KilometerRate create(KilometerRate kilometerRate) {
        if (kilometerRate == null) {
            throw new IllegalArgumentException("Kilometer rate is null");
        }
        em.persist(kilometerRate);
        return kilometerRate;
    }

    @Override
    public RateCategory create(RateCategory rateCategory) {
        if (rateCategory == null) {
            throw new IllegalArgumentException("Category rate is null");
        }
        em.persist(rateCategory);
        return rateCategory;
    }

    @Override
    @Transactional
    public KilometerRate edit(KilometerRate kilometerRate) {
        if (kilometerRate == null) {
            throw new IllegalArgumentException("Kilometer rate is null");
        }
        em.merge(kilometerRate);
        return kilometerRate;
    }

    @Override
    @Transactional
    public RateCategory edit(RateCategory rateCategory) {
        if (rateCategory == null) {
            throw new IllegalArgumentException("Category rate is null");
        }
        em.merge(rateCategory);
        return rateCategory;
    }

    @Override
    public List<KilometerRate> getAllKilometerRates() throws SQLException {
        try {
            return em.createQuery("SELECT kilometerRate FROM KilometerRate kilometerRate", KilometerRate.class).getResultList();
        }
        catch (Exception e) {
            throw new SQLException("Could not get all kilometer rates");
        }
    }

    @Override
    public List<RateCategory> getAllRateCategories() throws SQLException {
        try {
            return em.createQuery("SELECT rateCategory FROM RateCategory rateCategory", RateCategory.class).getResultList();
        }
        catch (Exception e) {
            throw new SQLException("Could not get all kilometer rates");
        }
    }

    @Override
    public List<KilometerRate> getAllKilometerRatesByRoad(int roadId) throws IllegalArgumentException {
        try {
            return em.createQuery("SELECT kilometerRate FROM KilometerRate kilometerRate WHERE Road.id = :roadId", KilometerRate.class)
                    .setParameter("roadId", roadId)
                    .getResultList();
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("The query or parameter is not correct");
        }
    }

    @Override
    public List<RateCategory> getAllRateCategoriesByRoad(int roadId) throws IllegalArgumentException {
        try {
            return em.createQuery("SELECT rateCategory FROM RateCategory rateCategory WHERE Road.id = :roadId", RateCategory.class)
                    .setParameter("roadId", roadId)
                    .getResultList();
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("The query or parameter is not correct");
        }
    }

    @Override
    public KilometerRate findKilometerRateById(int kilometerRateId) throws NoResultException, IllegalArgumentException {
        try {
            return em.createQuery("SELECT kilometerRate FROM KilometerRate kilometerRate WHERE kilometerRate.id = :kilometerRateId", KilometerRate.class)
                    .setParameter("kilometerRateId", kilometerRateId)
                    .getSingleResult();
        }
        catch (NoResultException e) {
            throw new NoResultException("Could not get any results");
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("The query or parameter is not correct");
        }
    }

    @Override
    public RateCategory findRateCategoryById(int rateCategoryId) throws NoResultException, IllegalArgumentException {
        try {
            return em.createQuery("SELECT rateCategory FROM RateCategory rateCategory WHERE rateCategory.id = :rateCategoryId", RateCategory.class)
                    .setParameter("rateCategoryId", rateCategoryId)
                    .getSingleResult();
        }
        catch (NoResultException e) {
            throw new NoResultException("Could not get any results");
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("The query or parameter is not correct");
        }
    }

    @Override
    public double calculateKilometerRatePriceByRateCategory(int kilomterRateId, int rateCategoryId) throws SQLException {
        KilometerRate kilometerRate = findKilometerRateById(kilomterRateId);
        RateCategory rateCategory = findRateCategoryById(rateCategoryId);
        double price = kilometerRate.getKilometerPrice() * rateCategory.getPercentagePrice();
        DecimalFormat df = new DecimalFormat(".##");
        df.format(price);
        return price;
    }

    /*@Override
    public RateCategory findRateCategoryByName(String rateCategorie) throws SQLException {
        try{
            em.createQuery("SELECT rateCategory FROM RateCategory rateCategory WHERE rateCategory.fuelEnum = :rateCategoryName AND rateCategory.kilometerRate.", RateCategory.class)
                    .setParameter("rateCategoryName", rateCategorie)
                    .getSingleResult();
        }
        catch(exception e){
            throw new SQLException("Dao could not find Ratecategory with name " + rateCategorie);
        }
        return null;
    }*/
}
