package bean;

import classes.Owner;
import classes.Vehicle;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import service.OwnerService;
import service.VehicleService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@SessionScoped
@Named(value = "carOwnerBean")
public class CarOwnerBean implements Serializable {

    @Inject
    private VehicleService vehicleService;
    @Inject
    private OwnerService ownerService;

    private Owner selectedOwner;
    private Vehicle selectedCar;

    @PostConstruct
    public void init() {

    }

    public Owner getSelectedOwner() {
        return selectedOwner;
    }

    public void setSelectedOwner(Owner selectedOwner) {
        this.selectedOwner = selectedOwner;
    }

    public Vehicle getSelectedCar() {
        return selectedCar;
    }

    public void setSelectedCar(Vehicle selectedCar) {
        this.selectedCar = selectedCar;
    }

    public void onOwnerRowSelect(SelectEvent event) {
        this.selectedOwner = (Owner) event.getObject();
        System.out.println("selecterdowner is :" + selectedOwner.getFirstName());
        FacesMessage msg = new FacesMessage("Owner Selected", ((Owner) event.getObject()).getLastName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onOwnerRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Owner Unselected", ((Owner) event.getObject()).getLastName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onVehicleRowSelect(SelectEvent event) {
        this.selectedCar = (Vehicle) event.getObject();
        System.out.println("SelectedVehicle is :" + selectedCar.getLicensePlate());
        FacesMessage msg = new FacesMessage("Vehicle Selected", ((Vehicle) event.getObject()).getLicensePlate());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onVehicleRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Vehicle Unselected", ((Vehicle) event.getObject()).getLicensePlate());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void linkOwnerToCar(){
        try{
            if(ownerService.linkVehicleToOwner(selectedCar.getID(), selectedOwner.getCitizenId()))
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Succesfully linked", "Owner " + selectedOwner.getLastName() + " succesfully linked to " + selectedCar.getLicensePlate()));
            }
            else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "LinkException", "Could not link the owner to the car. Something went wrong, please try again later. The car may already be assigned to an owner."));
            }
        }
        catch(Exception e)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "LinkException", "Could not link the owner to the car. Something went wrong, please try again later."));
        }

    }
}
