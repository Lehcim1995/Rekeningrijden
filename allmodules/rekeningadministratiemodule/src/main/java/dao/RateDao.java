package dao;

import domain.KilometerRate;
import domain.RateCategory;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public interface RateDao {

    KilometerRate create(KilometerRate kilometerRate);

    RateCategory create (RateCategory rateCategory);

    KilometerRate edit (KilometerRate kilometerRate);

    RateCategory edit (RateCategory rateCategory);

    List<KilometerRate> getAllKilometerRates() throws SQLException;

    List<RateCategory> getAllRateCategories() throws SQLException;

    List<KilometerRate> getAllKilometerRatesByRoad(int roadId) throws IllegalArgumentException;

    List<RateCategory> getAllRateCategoriesByRoad(int roadId) throws IllegalArgumentException;

    KilometerRate findKilometerRateById(int kilometerRateId) throws IllegalArgumentException, NoResultException;

    RateCategory findRateCategoryById(int rateCategoryId) throws IllegalArgumentException, NoResultException;

    double calculateKilometerRatePriceByRateCategory(int kilomterRateId, int rateCategoryId) throws SQLException;

    //RateCategory findRateCategoryByName(String rateCategorie) throws SQLException;
}
