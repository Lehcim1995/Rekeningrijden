package bean;

import classes.Owner;
import service.VehicleService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@RequestScoped
@Named(value = "ownerBean")
public class OwnerBean implements Serializable {

    @Inject
    private VehicleService vehicleService;
    private List<Owner> allOwners;

    @PostConstruct
    public void init() {

    }

    public List<Owner> getAllOwners() {
        return allOwners;
    }

    public void setAllOwners(List<Owner> allOwners) {
        this.allOwners = allOwners;
    }
}
