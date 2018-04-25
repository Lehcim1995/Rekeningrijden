package dao;

import classes.Owner;
import classes.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class OwnerDaoJPA implements OwnerDao {

    @PersistenceContext(unitName = "accountAdministrationPU")
    private EntityManager em;

    public void setEm(EntityManager em) {
        this.em = em;
    }


    @Override
    public Owner create(Owner owner) {
        if (owner == null) {
            throw new IllegalArgumentException("Owner is null");
        }
        em.persist(owner);
        return owner;
    }

    @Override
    public Owner update(Owner owner) {
        if (owner == null) {
            throw new IllegalArgumentException("Owner is null");
        }
        em.merge(owner);
        return owner;
    }

    @Override
    public Owner findOwnerById(int citizenId) {
        try {
            return em.createQuery("SELECT owner FROM Owner owner WHERE owner.citizenId = :citizenId", Owner.class)
                    .setParameter("citizenId", citizenId)
                    .getSingleResult();
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Owner> getAllOwners() {
        try {
            return em.createQuery("SELECT owner FROM Owner owner", Owner.class).getResultList();
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    @Transactional
    public boolean linkVehicleToOwner(Vehicle vehicle, Owner owner) {
        try {
                owner.add(vehicle);
                em.merge(owner);
                return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean linkPreviousVehicleToOwner(Vehicle vehicle, Owner owner) {
        try {
            owner.addPrevious(vehicle);
            em.merge(owner);
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}
