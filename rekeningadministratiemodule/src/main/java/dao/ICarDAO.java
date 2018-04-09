package dao;

import classes.Vehicle;

import java.util.List;

public interface ICarDAO {

    List<Vehicle> getVehicles();

    Vehicle getVehicle(int id);

    Vehicle AddVehicle(Vehicle  vehicle);

    Vehicle AssignzCarTracker(Vehicle vehicle);

    boolean IsUniqueCarTracker(String carTracker);
}
