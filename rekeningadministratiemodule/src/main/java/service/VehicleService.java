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

    public List<VehicleTracker> getVehicleTrackers() {
        return vehicleDao.getVehicleTrackers();
    }

    public VehicleTracker getVehicleTrackerByID(String ID) {
        if(ID == null || ID.isEmpty()) return null;
        else return vehicleDao.getVehicleTrackerByID(ID);
    }

    public VehicleTracker createVehicleTracker(VehicleTracker tracker) {
        if(tracker != null) return vehicleDao.createVehicleTracker(tracker);
        else return null;
    }

    public List<Vehicle> getVehicles() {
        return vehicleDao.getVehicles();
    }

    public Vehicle getVehicleByID(int ID) {
        if(ID <= 0) return null;
        else return vehicleDao.getVehicleByID(ID);
    }

    public Vehicle getVehicleByVehicleTracker(String ID) {
        if(ID == null || ID.isEmpty()) return null;
        else return vehicleDao.getVehicleByVehicleTracker(ID);
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        if(vehicle != null) return vehicleDao.createVehicle(vehicle);
        else return null;
    }

    public void setVehicleTracker(int vehicleID, String vehicleTrackerID) {
        vehicleDao.setVehicleTracker(vehicleID, vehicleTrackerID);
    }
}
