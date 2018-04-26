package service;

import classes.KilometerRate;
import classes.Road;
import dao.RoadDao;

import javax.inject.Inject;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class RoadService implements Serializable {

    @Inject
    private RoadDao roadDao;

    public Road createRoad(String name, List<String> geocodedAdresses, KilometerRate kilometerRate) {
        try {
            Road road = new Road(name, geocodedAdresses, kilometerRate);
            return roadDao.createRoad(road);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Could not create road");
        }
    }

    public Road editRoad(String name, List<String> geocodedAdresses, KilometerRate kilometerRate) {
        try {
            Road road = new Road(name, geocodedAdresses, kilometerRate);
            return roadDao.editRoad(road);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Could not edit road");
        }
    }

    public boolean deleteRoad(int id) throws SQLException {
        try {
            return roadDao.deleteRoad(id);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Could not delete road with id: " + id);
        }
    }

    public Road findRoadById(int id) throws SQLException {
        try {
            return roadDao.findRoadById(id);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Could nog find road with id: " + id);
        }
    }

    public List<Road> getAllRoads() throws SQLException {
        try {
            return roadDao.getAllRoads();
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Could not get all roads");
        }
    }
}
