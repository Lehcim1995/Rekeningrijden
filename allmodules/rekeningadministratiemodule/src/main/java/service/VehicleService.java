package service;

import Exceptions.CreationException;
import dao.VehicleDao;
import domain.FuelEnum;
import domain.Vehicle;
import domain.VehicleTracker;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Stateless
public class VehicleService implements Serializable {

    @Inject
    VehicleDao vehicleDao;


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

    public VehicleTracker createVehicleTrackerId(String Id) {
        if (Id != null) {
            return vehicleDao.createVehicleTrackerId(Id);
        }
        else {
            return null;
        }
    }
    public VehicleTracker createVehicleTrackerId(String Id, String manufacturer) throws CreationException {
        if(!manufacturer.isEmpty() && manufacturer != null && !Id.isEmpty() && Id != null) {
            return vehicleDao.createVehicleTrackerId(Id, manufacturer);
        }
        else {
            return null;
        }
    }

    public List<Vehicle> getVehicles() {
        return vehicleDao.getVehicles();
    }

    public Vehicle getVehicleByLicencePlate(String plate)
    {
        return vehicleDao.getVehicleByLicencePlate(plate);
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

    public Vehicle createVehicleParam(String licensePlate, Date buildYear, int weight, String fueltype) throws CreationException {
        FuelEnum fuelType = null;
        try
        {
            fuelType = FuelEnum.valueOf(fueltype);
        }
        catch(IllegalArgumentException e){
            throw new CreationException("Something went wrong when creating the car. " + fueltype + " is not a valid value.");
        }
        if (licensePlate != null && !licensePlate.isEmpty() && buildYear != null) {
            return vehicleDao.createVehicleParam(licensePlate, buildYear, weight, fuelType);
        }
        else {
            return null;
        }
    }

    public void setVehicleTracker(int vehicleID, String vehicleTrackerID) {
        vehicleDao.setVehicleTracker(vehicleID, vehicleTrackerID);
    }

    public Vehicle editVehicle(int vehicleId, int weight, String licenseplate, FuelEnum fuelType, Date buildYear) throws IllegalArgumentException{
        Vehicle vehicle = vehicleDao.getVehicleByID(vehicleId);
        vehicle.setWeight(weight);
        vehicle.setLicensePlate(licenseplate);
        vehicle.setFueltype(fuelType);
        vehicle.setBuildYear(buildYear);
        return vehicleDao.editVehicle(vehicle);
    }

    public List<Vehicle> getCarsOfOwner(String ownerid) {
        return vehicleDao.getCarsOfOwner(ownerid);
    }
}
