package dao;

import classes.Vehicle;
import classes.VehicleTracker;

import java.util.List;

public interface VehicleDao {

    List<VehicleTracker> getVehicleTrackers();

    VehicleTracker getVehicleTrackerByID(String ID);

    VehicleTracker createVehicleTracker(VehicleTracker tracker);

    List<Vehicle> getVehicles();

    Vehicle getVehicleByID(int ID);

    Vehicle getVehicleByVehicleTracker(String ID);

    Vehicle createVehicle(Vehicle vehicle);

    void setVehicleTracker(int vehicleID, String vehicleTrackerID);
}
