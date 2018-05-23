package dao;

import domain.Road;

import java.sql.SQLException;
import java.util.List;

public interface RoadDao {

    Road createRoad (Road road);

    Road editRoad (Road road);

    boolean deleteRoad (int id) throws SQLException;

    Road findRoadById (int id) throws SQLException;

    List<Road> getAllRoads () throws SQLException;
}
