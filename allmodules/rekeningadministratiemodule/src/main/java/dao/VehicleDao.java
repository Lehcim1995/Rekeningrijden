package dao;

import Exceptions.CreationException;
import domain.FuelEnum;
import domain.Vehicle;
import domain.VehicleTracker;

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

    Vehicle createVehicleParam(String licensePlate, Date buildYear, int weight, FuelEnum fueltype);

    Vehicle getVehicleByVehicleTracker(String ID);

    Vehicle createVehicle(Vehicle vehicle);

    void setVehicleTracker(int vehicleID, String vehicleTrackerID);
}
