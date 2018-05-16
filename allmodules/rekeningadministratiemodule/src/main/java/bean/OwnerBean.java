package bean;

import domain.Owner;
import service.OwnerService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named(value = "ownerBean")
public class OwnerBean implements Serializable {

    @Inject
    private OwnerService ownerService;
    private List<Owner> allOwners;

    @PostConstruct
    public void init() {

    }

    public List<Owner> getAllOwners() {
        return this.ownerService.getAllOwners();
    }

    public void setAllOwners(List<Owner> allOwners) {
        this.allOwners = allOwners;
    }
}
