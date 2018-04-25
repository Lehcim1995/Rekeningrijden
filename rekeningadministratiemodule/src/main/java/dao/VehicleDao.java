package dao;

import Exceptions.CreationException;
import classes.FuelEnum;
import classes.RateCategory;
import classes.Vehicle;
import classes.VehicleTracker;

import java.util.Date;
import java.util.List;

public interface VehicleDao {

    List<VehicleTracker> getVehicleTrackers();

    VehicleTracker getVehicleTrackerByID(String ID);

    VehicleTracker createVehicleTracker(VehicleTracker tracker);

    VehicleTracker createVehicleTrackerId(String Id);

    VehicleTracker createVehicleTrackerId(String Id, String manufacturer) throws CreationException;

    List<Vehicle> getVehicles();

    Vehicle getVehicleByID(int ID);

    Vehicle createVehicleParam(RateCategory rateCategorie, String licensePlate, Date buildYear, int weight, FuelEnum fueltype);

    Vehicle getVehicleByVehicleTracker(String ID);

    Vehicle createVehicle(Vehicle vehicle);

    void setVehicleTracker(int vehicleID, String vehicleTrackerID);
}
