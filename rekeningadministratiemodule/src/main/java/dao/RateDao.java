package dao;

import classes.KilometerRate;
import classes.RateCategory;

import java.sql.SQLException;
import java.util.List;

public interface RateDao {

    KilometerRate create(KilometerRate kilometerRate);

    RateCategory create (RateCategory rateCategory);

    KilometerRate edit (KilometerRate kilometerRate);

    RateCategory edit (RateCategory rateCategory);

    List<KilometerRate> getAllKilometerRates() throws SQLException;

    List<RateCategory> getAllRateCategories() throws SQLException;

    KilometerRate findKilometerRateById(int kilometerRateId) throws SQLException;

    RateCategory findRateCategoryById(int rateCategoryId) throws SQLException;

    double calculateKilometerRatePriceByRateCategory(int kilomterRateId, int rateCategoryId) throws SQLException;
}
