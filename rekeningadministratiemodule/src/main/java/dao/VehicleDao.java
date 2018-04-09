package dao;

import classes.Vehicle;
import classes.VehicleTracker;

import java.util.List;

public interface VehicleDao {

    List<VehicleTracker> GetVehicleTrackers();

    VehicleTracker GetVehicleTrackerByID(String ID);

    VehicleTracker CreateVehicleTracker(VehicleTracker tracker);

    List<Vehicle> GetVehicles();

    Vehicle GetVehicleByID(int ID);

    Vehicle GetVehicleByVehicleTracker(String ID);

    Vehicle CreateVehicle(Vehicle vehicle);

    void SetVehicleTracker(int vehicleID, String vehicleTrackerID);
}
