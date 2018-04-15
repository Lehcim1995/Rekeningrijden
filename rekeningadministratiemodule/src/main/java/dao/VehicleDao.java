package dao;

import Exceptions.CreationException;
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

    Vehicle getVehicleByVehicleTracker(String ID);

    Vehicle createVehicle(Vehicle vehicle);

    Vehicle createVehicleParam(String rateCategorie, String licensePlate, Date buildYear);

    void setVehicleTracker(int vehicleID, String vehicleTrackerID);
}
