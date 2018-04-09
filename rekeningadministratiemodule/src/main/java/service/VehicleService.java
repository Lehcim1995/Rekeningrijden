package service;

import classes.Vehicle;
import classes.VehicleTracker;
import dao.VehicleDao;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class VehicleService implements Serializable {

    @Inject
    private VehicleDao vehicleDao;

    public VehicleService() {}

    public List<VehicleTracker> GetVehicleTrackers() {
        return vehicleDao.GetVehicleTrackers();
    }

    public VehicleTracker GetVehicleTrackerByID(String ID) {
        if(ID == null || ID.isEmpty()) return null;
        else return vehicleDao.GetVehicleTrackerByID(ID);
    }

    public VehicleTracker CreateVehicleTracker(VehicleTracker tracker) {
        if(tracker != null) return vehicleDao.CreateVehicleTracker(tracker);
        else return null;
    }

    public List<Vehicle> GetVehicles() {
        return vehicleDao.GetVehicles();
    }

    public Vehicle GetVehicleByID(int ID) {
        if(ID <= 0) return null;
        else return vehicleDao.GetVehicleByID(ID);
    }

    public Vehicle GetVehicleByVehicleTracker(String ID) {
        if(ID == null || ID.isEmpty()) return null;
        else return vehicleDao.GetVehicleByVehicleTracker(ID);
    }

    public Vehicle CreateVehicle(Vehicle vehicle) {
        if(vehicle != null) return vehicleDao.CreateVehicle(vehicle);
        else return null;
    }

    public void SetVehicleTracker(int vehicleID, String vehicleTrackerID) {
        vehicleDao.SetVehicleTracker(vehicleID, vehicleTrackerID);
    }
}
