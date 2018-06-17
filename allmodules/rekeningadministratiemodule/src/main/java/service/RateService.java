package service;

import dao.RateDao;
import dao.RoadDao;
import domain.FuelEnum;
import domain.KilometerRate;
import domain.RateCategory;
import domain.Road;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RateService implements Serializable {

    @Inject
    private RateDao rateDao;

    @Inject
    private RoadDao roadDao;

    public KilometerRate create(Road road, double kilometerPrice, Date startDate, Date endDate) {
        /*if (startDate.before(new Date())) {
            throw new IllegalArgumentException("Start date can not be set before today");
        }
        if (endDate.before(new Date()) || endDate.before(startDate)) {
            throw new IllegalArgumentException("End date can not be set before start date or today");
        }*/
        if (kilometerPrice < 0) {
            throw new IllegalArgumentException("Price cannot be lower than 0");
        }

        try {
            KilometerRate kilometerRate = new KilometerRate(road, kilometerPrice, startDate, endDate);
            return rateDao.create(kilometerRate);
        }
        catch(Exception e) {
            throw new IllegalArgumentException("Could not create kilometer rate");
        }
    }

    public RateCategory create(FuelEnum fuelEnum, Road road, double percentagePrice, Date startDate, Date endDate) {
        //TODO:foolproof creationdate
        /*if (startDate.before(new Date())) {
            throw new IllegalArgumentException("Start date can not be set before today");
        }*/
        /*if (endDate.after(startDate)) {
            throw new IllegalArgumentException("End date can not be set before start date or today");
        }*/
        if (percentagePrice < 0) {
            throw new IllegalArgumentException("Price cannot be lower than 0");
        }

        try {
            RateCategory rateCategory = new RateCategory(fuelEnum, road, percentagePrice, startDate, endDate);
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

    public List<KilometerRate> getAllKilomterRatesByRoad(int roadId) throws IllegalArgumentException {
        return  rateDao.getAllKilometerRatesByRoad(roadId);
    }

    public List<RateCategory> getAllRateCategoriesByRoad(int roadId) throws IllegalArgumentException {
        return rateDao.getAllRateCategoriesByRoad(roadId);
    }

    public KilometerRate findKilometerRateById(int kilometerRateId) throws IllegalArgumentException, NoResultException {
        try {
            return rateDao.findKilometerRateById(kilometerRateId);
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Could not find kilometer rate with id: " + kilometerRateId);
        }
    }

    public RateCategory findRateCategoryById(int rateCategoryId) throws IllegalArgumentException, NoResultException{
        try {
            return rateDao.findRateCategoryById(rateCategoryId);
        }
        catch (IllegalArgumentException e) {
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

    public KilometerRate editKilometerRate(int kilometerRateId, double kilometerPrice, Date startDate) throws SQLException {
        try {
            KilometerRate kilometerRate = findKilometerRateById(kilometerRateId);
            kilometerRate.setEndDate(new Date(startDate.getTime() - 60000));

            rateDao.edit(kilometerRate);

            Road road = kilometerRate.getRoad();

            List<KilometerRate> previousRates = road.getPreviousRates();
            previousRates.add(kilometerRate);

            KilometerRate finalRate = rateDao.create(new KilometerRate(road, kilometerPrice, startDate, null));

            road.setPreviousRates(previousRates);
            road.setKilometerRate(finalRate);
            roadDao.editRoad(road);

            return finalRate;
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Could not edit kilometer price");
        }
    }

    public KilometerRate editKilometerRate(Road changedRoad) throws SQLException {
        try {
            KilometerRate newKilometerRate = changedRoad.getKilometerRate();

            KilometerRate kilometerRate = findKilometerRateById(newKilometerRate.getId());

            if (kilometerRate != null && newKilometerRate.getKilometerPrice() != kilometerRate.getKilometerPrice() && newKilometerRate.getStartDate() != kilometerRate.getStartDate()) {

                kilometerRate.setEndDate(new Date(newKilometerRate.getStartDate().getTime() - 60000));

                rateDao.edit(kilometerRate);

                Road road = kilometerRate.getRoad();

                List<KilometerRate> previousRates = road.getPreviousRates();
                previousRates.add(kilometerRate);

                KilometerRate finalRate = rateDao.create(new KilometerRate(newKilometerRate));

                road.setPreviousRates(previousRates);
                road.setKilometerRate(finalRate);
                roadDao.editRoad(road);

                return finalRate;
            }
        }
        catch (Exception e) {
                throw new IllegalArgumentException("Could not edit kilometer price");
        }
        return null;
    }

    public RateCategory edit(int rateCategoryId, double percentagePrice, Date startDate) {
        try {
            RateCategory rateCategory = findRateCategoryById(rateCategoryId);
            rateCategory.setEndDate(new Date(startDate.getTime() - 60000));

            rateDao.edit(rateCategory);

            Road road = rateCategory.getRoad();

            List<RateCategory> previousCategories = road.getPreviousCategories();
            previousCategories.add(rateCategory);

            RateCategory finalCategory = rateDao.create(new RateCategory(rateCategory.getFuelEnum(), road, percentagePrice, startDate, null));

            List<RateCategory> currentCategories = road.getRateCategories();
            currentCategories.remove(rateCategory);
            currentCategories.add(finalCategory);

            road.setPreviousCategories(previousCategories);
            road.setRateCategories(currentCategories);
            roadDao.editRoad(road);

            return finalCategory;
            /*RateCategory rateCategory = findRateCategoryById(rateCategoryId);
            rateCategory.setPercentagePrice(percentagePrice);
            return rateDao.edit(rateCategory);*/
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Could not edit rate category percentage price");
        }
    }

    public RateCategory edit(RateCategory category) {
        try {
            RateCategory rateCategory = findRateCategoryById(category.getId());
            rateCategory.setEndDate(new Date(category.getStartDate().getTime() - 60000));

            rateDao.edit(rateCategory);

            Road road = rateCategory.getRoad();

            List<RateCategory> previousCategories = road.getPreviousCategories();
            previousCategories.add(rateCategory);

            RateCategory finalCategory = rateDao.create(new RateCategory(category.getFuelEnum(), road, category.getPercentagePrice(), category.getStartDate(), null));

            List<RateCategory> currentCategories = road.getRateCategories();
            currentCategories.remove(rateCategory);
            currentCategories.add(finalCategory);

            road.setPreviousCategories(previousCategories);
            road.setRateCategories(currentCategories);
            roadDao.editRoad(road);

            return finalCategory;
            /*RateCategory rateCategory = findRateCategoryById(rateCategoryId);
            rateCategory.setPercentagePrice(percentagePrice);
            return rateDao.edit(rateCategory);*/
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Could not edit rate category percentage price");
        }
    }

    /*public RateCategory findRateCategoryByName(String rateCategorie) {
        try{
            return rateDao.findRateCategoryByName(rateCategorie);
        }
        catch(exception e)
        {
            throw new IllegalArgumentException("Could not find rate category with name "+ rateCategorie);
        }
    }*/
}
