package service;

import classes.FuelEnum;
import classes.KilometerRate;
import classes.RateCategory;
import classes.Road;
import dao.RateDao;

import javax.inject.Inject;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class RateService implements Serializable {

    @Inject
    private RateDao rateDao;

    public KilometerRate create(Road road, double kilometerPrice, Date startDate, Date endDate, RateCategory rateCategoryEnum) {
        if (startDate.before(new Date())) {
            throw new IllegalArgumentException("Start date can not be set before today");
        }
        if (endDate.before(new Date()) || endDate.before(startDate)) {
            throw new IllegalArgumentException("End date can not be set before start date or today");
        }
        if (kilometerPrice < 0) {
            throw new IllegalArgumentException("Price cannot be lower than 0");
        }

        try {
            KilometerRate kilometerRate = new KilometerRate(road, kilometerPrice, startDate, endDate, rateCategoryEnum);
            return rateDao.create(kilometerRate);
        }
        catch(Exception e) {
            throw new IllegalArgumentException("Could not create kilometer rate");
        }
    }

    public RateCategory create(FuelEnum fuelEnum, double percentagePrice, Date startDate, Date endDate) {
        if (startDate.before(new Date())) {
            throw new IllegalArgumentException("Start date can not be set before today");
        }
        if (endDate.before(new Date()) || endDate.before(startDate)) {
            throw new IllegalArgumentException("End date can not be set before start date or today");
        }
        if (percentagePrice < 0) {
            throw new IllegalArgumentException("Price cannot be lower than 0");
        }

        try {
            RateCategory rateCategory = new RateCategory(fuelEnum, percentagePrice, startDate, endDate);
            return rateDao.create(rateCategory);
        }
        catch(Exception e) {
            throw new IllegalArgumentException("Could not create rate category");
        }
    }

    public List<KilometerRate> getAllKilometerRates() throws SQLException {
        return rateDao.getAllKilometerRates();
    }

    public List<RateCategory> getAllRateCategories() throws SQLException {
        return rateDao.getAllRateCategories();
    }

    public KilometerRate findKilometerRateById(int kilometerRateId) throws SQLException {
        try {
            return rateDao.findKilometerRateById(kilometerRateId);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Could not find kilometer rate with id: " + kilometerRateId);
        }
    }

    public RateCategory findRateCategoryById(int rateCategoryId) {
        try {
            return rateDao.findRateCategoryById(rateCategoryId);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Could not find category rate with id: " + rateCategoryId);
        }
    }

    public double calculateKilometerRatePriceByRateCategory(int kilomterRateId, int rateCategoryId) throws SQLException {
        try {
            return rateDao.calculateKilometerRatePriceByRateCategory(kilomterRateId, rateCategoryId);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Could not calculate price");
        }
    }

    public KilometerRate editKilometerRate(int kilometerRateId, double kilometerPrice) throws SQLException {
        try {
            KilometerRate kilometerRate = findKilometerRateById(kilometerRateId);
            kilometerRate.setKilometerPrice(kilometerPrice);
            return rateDao.edit(kilometerRate);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Could not edit kilometer price");
        }
    }

    public RateCategory edit(int rateCategoryId, double percentagePrice) {
        try {
            RateCategory rateCategory = findRateCategoryById(rateCategoryId);
            rateCategory.setPercentagePrice(percentagePrice);
            return rateDao.edit(rateCategory);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Could not edit rate category percentage price");
        }
    }

    /*public RateCategory findRateCategoryByName(String rateCategorie) {
        try{
            return rateDao.findRateCategoryByName(rateCategorie);
        }
        catch(Exception e)
        {
            throw new IllegalArgumentException("Could not find rate category with name "+ rateCategorie);
        }
    }*/
}
