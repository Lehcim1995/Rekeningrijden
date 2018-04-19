package bean;

import classes.Owner;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import service.VehicleService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@RequestScoped
@Named(value = "carOwnerBean")
public class CarOwnerBean implements Serializable {

    @Inject
    private VehicleService vehicleService;

    private Owner selectedOwner;
    private Owner selectedCar;

    @PostConstruct
    public void init() {

    }

    public Owner getSelectedOwner() {
        return selectedOwner;
    }

    public void setSelectedOwner(Owner selectedOwner) {
        this.selectedOwner = selectedOwner;
    }

    public Owner getSelectedCar() {
        return selectedCar;
    }

    public void setSelectedCar(Owner selectedCar) {
        this.selectedCar = selectedCar;
    }

    public void onOwnerRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Owner Selected", ((Owner) event.getObject()).getLastName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onOwnerRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Owner Unselected", ((Owner) event.getObject()).getLastName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onVehicleRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Owner Selected", ((Owner) event.getObject()).getLastName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onVehicleRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Owner Unselected", ((Owner) event.getObject()).getLastName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void linkOwnerToCar(){

    }
}
