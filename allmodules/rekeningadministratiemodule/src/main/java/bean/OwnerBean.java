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
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named(value = "ownerBean")
public class OwnerBean implements Serializable {

    @Inject
    private OwnerService ownerService;
    private Owner selectedOwner;
    private List<Owner> allOwners;

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

    public List<Owner> getAllCarOwners()
    {
        try{
            return ownerService.getAllOwners();
        }
        catch(IllegalArgumentException e)
        {
            FacesMessage msg = new FacesMessage("Something went wrong when getting al the Owners " +
                    e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }
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

    public void onOwnerEdit(CellEditEvent event) {
        String oldValue = event.getOldValue().toString();
        String newValue = event.getNewValue().toString();

        if (newValue != null && !newValue.equals(oldValue)) {
            try {
                Owner entity = (Owner) ((DataTable) event.getComponent()).getRowData();
                //TODO set new ownervalues to owner.
                //rateService.editKilometerRate(entity.getKilometerRate().getId(), Double.valueOf(newValue));
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell edited", "Old: " + oldValue + ", New:" + newValue);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Something went wrong while editing the cell", "error: " + e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    }

    public List<Owner> getAllOwners() {
        return allOwners;
    }

    public void setAllOwners(List<Owner> allOwners) {
        this.allOwners = allOwners;
    }
}
