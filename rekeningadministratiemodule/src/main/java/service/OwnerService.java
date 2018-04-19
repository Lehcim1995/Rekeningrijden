package service;

import classes.Owner;
import classes.Vehicle;
import dao.OwnerDao;
import dao.VehicleDao;

import javax.inject.Inject;
import java.util.List;

public class OwnerService {

    @Inject
    private OwnerDao ownerDao;

    @Inject
    private VehicleDao vehicleDao;

    public boolean create(int citizenId, String firstName, String middleName, String lastName, String address, String city, String accountNumber, String password) {
        try {
            Owner owner = new Owner(citizenId, firstName, middleName, lastName, address, city, accountNumber, password);
            if (ownerDao.create(owner) != null) {
                return true;
            }
        }
        catch (Exception e) {
            e.getMessage();
        }
        return false;
    }


    public boolean update(int citizenId, String firstName, String middleName, String lastName, String address, String city, String accountNumber, String password){
        try {
            Owner owner = new Owner(citizenId, firstName, middleName, lastName, address, city, accountNumber, password);
            if (ownerDao.update(owner) != null) {
                return true;
            }
        }
        catch (Exception e) {
            e.getMessage();
        }
        return false;
    }

    public Owner findOwnerById(int citizenId){
        return ownerDao.findOwnerById(citizenId);
    }

    public List<Owner> getAllOwners(){
        return ownerDao.getAllOwners();
    }

    public boolean linkVehicleToOwner(int vehicleId, int citizenId) {
        try {
            Vehicle vehicle = vehicleDao.getVehicleByID(vehicleId);
            Owner owner = ownerDao.findOwnerById(citizenId);
            ownerDao.linkVehicleToOwner(vehicle, owner);
            return true;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean linkPreviousVehicleToOwner(int vehicleId, int citizenId) {
        try {
            Vehicle vehicle = vehicleDao.getVehicleByID(vehicleId);
            Owner owner = ownerDao.findOwnerById(citizenId);
            ownerDao.linkPreviousVehicleToOwner(vehicle, owner);
            return true;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
