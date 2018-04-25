package dao;

import classes.KilometerRate;
import classes.Owner;
import classes.RateCategory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class RateDaoJPA implements RateDao{

    @PersistenceContext(unitName = "accountAdministrationPU")
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
    public KilometerRate edit(KilometerRate kilometerRate) {
        if (kilometerRate == null) {
            throw new IllegalArgumentException("Kilometer rate is null");
        }
        em.merge(kilometerRate);
        return kilometerRate;
    }

    @Override
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
    public KilometerRate findKilometerRateById(int kilometerRateId) throws SQLException {
        try {
            return em.createQuery("SELECT kilometerRate FROM KilometerRate kilometerRate WHERE kilometerRate.id = :kilometerRateId", KilometerRate.class)
                    .setParameter("kilometerRateId", kilometerRateId)
                    .getSingleResult();
        }
        catch (Exception e) {
            throw new SQLException("Could not find kilometer rate with id: " + kilometerRateId);
        }
    }

    @Override
    public RateCategory findRateCategoryById(int rateCategoryId) throws SQLException {
        try {
            return em.createQuery("SELECT rateCategory FROM RateCategory rateCategory WHERE rateCategory.id = :rateCategoryId", RateCategory.class)
                    .setParameter("rateCategoryId", rateCategoryId)
                    .getSingleResult();
        }
        catch (Exception e) {
            throw new SQLException("Could not find category rate with id: " + rateCategoryId);
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
}
