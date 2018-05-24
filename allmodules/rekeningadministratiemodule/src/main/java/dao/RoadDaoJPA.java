package dao;

import domain.Road;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;
import java.util.List;

public class RoadDaoJPA implements RoadDao {

    @PersistenceContext(name = "accountAdministrationPU")
    private EntityManager em;

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public Road createRoad(Road road) {
        if (road == null) {
            throw new IllegalArgumentException("Road is null");
        }
        em.persist(road);
        return road;
    }

    @Override
    public Road editRoad(Road road) {
        if (road == null) {
            throw new IllegalArgumentException("Road is null");
        }
        em.merge(road);
        return road;
    }

    @Override
    public boolean deleteRoad(int id) throws SQLException {
        em.remove(findRoadById(id));
        return true;
    }

    @Override
    public Road findRoadById(int id) throws SQLException {
        try {
            return em.createQuery("SELECT road FROM Road road WHERE road.id = :id", Road.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }
        catch (Exception e) {
            throw new SQLException("Could not find road with id: " + id);
        }
    }

    @Override
    public List<Road> getAllRoads() throws IllegalArgumentException {
        try {
            return em.createQuery("SELECT road FROM Road road", Road.class).getResultList();
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Could not get all roads");
        }
    }
}
