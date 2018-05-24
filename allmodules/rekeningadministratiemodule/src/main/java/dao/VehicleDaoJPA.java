package dao;

import Exceptions.CreationException;
import domain.FuelEnum;
import domain.Vehicle;
import domain.VehicleTracker;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TransactionRequiredException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Stateless
public class VehicleDaoJPA implements VehicleDao, Serializable {

    @PersistenceContext(name = "accountAdministrationPU")
    private EntityManager em;
    private CriteriaBuilder cb;
    private CriteriaQuery<VehicleTracker> cvt;
    private CriteriaQuery<Vehicle> cv;
    private Root<VehicleTracker> vtr;
    private Root<Vehicle> vr;

    @PostConstruct
    public void init() {
        System.out.print("Initializing queries");

        cb = em.getCriteriaBuilder();
        setupVehicleTrackerJPA();
        setupVehicleJPA();
    }

    @Override
    public List<VehicleTracker> getVehicleTrackers() {
        setupVehicleTrackerJPA();
        return em.createQuery(cvt).getResultList();
    }

    @Override
    public VehicleTracker getVehicleTrackerByID(String ID) {
        setupVehicleTrackerJPA();
        return em.find(VehicleTracker.class, ID);
    }

    @Override
    public VehicleTracker createVehicleTracker(VehicleTracker tracker) {

        VehicleTracker vehicleTracker = new VehicleTracker(tracker);
        em.persist(vehicleTracker);

        return vehicleTracker;
    }

    @Override
    public VehicleTracker createVehicleTrackerId(String Id) {
        VehicleTracker vehicleTracker = new VehicleTracker(Id);
        em.persist(vehicleTracker);

        return vehicleTracker;
    }
    @Override
    public VehicleTracker createVehicleTrackerId(String Id, String manufacturer) throws CreationException {
        try{
            VehicleTracker vehicleTracker = new VehicleTracker(Id, manufacturer);
            em.persist(vehicleTracker);
            return vehicleTracker;
        }
        catch(IllegalArgumentException | TransactionRequiredException | EntityExistsException e)
        {
            throw new CreationException("Could not create Vehicletracker due to an error: " + e.getMessage());
        }

    }


    @Override
    public List<Vehicle> getVehicles() {
        setupVehicleJPA();
        return em.createQuery(cv).getResultList();
    }

    @Override
    public Vehicle getVehicleByID(int ID) {
        setupVehicleJPA();
        return em.find(Vehicle.class, ID);
    }

    @Override
    public Vehicle createVehicle(Vehicle vehicle) {

        Vehicle createdVehicle = new Vehicle(vehicle);
        em.persist(createdVehicle);

        return createdVehicle;
    }

    @Override
    public Vehicle createVehicleParam(String licensePlate, Date buildYear, int weight, FuelEnum fueltype) {
        Vehicle createdVehicle = new Vehicle(licensePlate, buildYear, weight, fueltype);
        em.persist(createdVehicle);

        return createdVehicle;
    }

    @Override
    public Vehicle getVehicleByVehicleTracker(String ID) {

        VehicleTracker tracker = em.find(VehicleTracker.class, ID);

        if (tracker != null) return tracker.getVehicle();
        else return null;
    }

    @Override
    public void setVehicleTracker(int vehicleID, String vehicleTrackerID) {

        Vehicle vehicle = em.find(Vehicle.class, vehicleID);
        VehicleTracker tracker = em.find(VehicleTracker.class, vehicleTrackerID);

        if (vehicle != null && tracker != null) {
            vehicle.setTracker(tracker);
            em.merge(vehicle);
        }
    }

    @Override
    public Vehicle editVehicle(Vehicle vehicle) throws IllegalArgumentException{
        try {
            em.merge(vehicle);
            return vehicle;
        }
        catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void setupVehicleTrackerJPA() {
        cvt = cb.createQuery(VehicleTracker.class);
        vtr = cvt.from(VehicleTracker.class);
        cvt.select(vtr);
    }

    private void setupVehicleJPA() {
        cv = cb.createQuery(Vehicle.class);
        vr = cv.from(Vehicle.class);
        cv.select(vr);
    }
}