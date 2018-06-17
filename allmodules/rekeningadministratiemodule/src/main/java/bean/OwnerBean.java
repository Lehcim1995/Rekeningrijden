package bean;

import domain.Owner;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import service.OwnerService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
@Named(value = "ownerBean")
public class OwnerBean implements Serializable {

    @Inject
    private OwnerService ownerService;
    private Owner selectedOwner;
    private List<Owner> allOwners;
    private String city;

    @PostConstruct
    public void init() {
        allOwners = getAllCarOwners();
    }

    public Owner getSelectedOwner() {
        return selectedOwner;
    }

    public void setSelectedOwner(Owner selectedOwner) {
        this.selectedOwner = selectedOwner;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Owner> getAllCarOwners() {
        if (allOwners == null) {
            try {
                allOwners = new ArrayList<Owner>();
                allOwners = ownerService.getAllOwners();
            } catch (IllegalArgumentException e) {
                FacesMessage msg = new FacesMessage("Something went wrong when getting al the Owners " +
                        e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return null;
            }
        }
        return allOwners;
    }


    public void onOwnerRowSelect(SelectEvent event) {
        //TODO: Cast to Road Class
        this.selectedOwner = ((Owner)event.getObject());
        FacesMessage msg = new FacesMessage("Owner selected", this.selectedOwner.getFirstName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onOwnerRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Owner Unselected", ((Owner) event.getObject()).getFirstName());
        this.selectedOwner = null;
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onValueQuantityChange(ValueChangeEvent event) {
        city = (String) event.getNewValue();
    }

    public void onOwnerEdit(ValueChangeEvent event) {
        String oldValue = event.getOldValue().toString();

        if (city != null && !city.equals(oldValue)) {
            try {
                Owner entity = (Owner) ((DataTable) event.getComponent()).getRowData();
                //TODO set new ownervalues to owner.
                //rateService.editKilometerRate(entity.getKilometerRate().getId(), Double.valueOf(newValue));
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell edited", "Old: " + oldValue + ", New:" + city);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Something went wrong while editing the cell", "error: " + e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    }

    public List<Owner> getAllOwners() {
        return ownerService.getAllOwners();
    }

//    public void setAllOwners(List<Owner> allOwners) {
//        this.allOwners = allOwners;
//    }
}
