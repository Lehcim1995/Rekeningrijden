package dao;

import domain.Owner;
import domain.Vehicle;

import java.util.List;

public interface OwnerDao {

    Owner create(Owner owner);

    Owner update(Owner owner);

    Owner findOwnerById(int citizenId);

    List<Owner> getAllOwners();

    boolean linkVehicleToOwner(Vehicle vehicle, Owner owner);

    boolean linkPreviousVehicleToOwner(Vehicle vehicle, Owner owner);

    void setOwnerUsingRRA(Owner ownerById);
}
