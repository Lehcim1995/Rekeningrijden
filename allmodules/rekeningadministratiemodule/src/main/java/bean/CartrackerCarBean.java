package bean;

import domain.Owner;
import domain.Vehicle;
import domain.VehicleTracker;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import service.VehicleService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@SessionScoped
@Named(value="cartrackerCarBean")
public class CartrackerCarBean implements Serializable {

    @Inject
    private VehicleService vehicleService;

    private Vehicle selectedCar;
    private VehicleTracker selectedVehicleTracker;

    @PostConstruct
    public void init() {

    }

    public Vehicle getSelectedCar() {
        return selectedCar;
    }

    public void setSelectedCar(Vehicle selectedCar) {
        this.selectedCar = selectedCar;
    }

    public VehicleTracker getSelectedVehicleTracker() {
        return selectedVehicleTracker;
    }

    public void setSelectedVehicleTracker(VehicleTracker selectedVehicleTracker) {
        this.selectedVehicleTracker = selectedVehicleTracker;
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

    public void onVehicleTrackerRowSelect(SelectEvent event) {
        this.selectedVehicleTracker = (VehicleTracker) event.getObject();
        System.out.println("SelectedVehicleTracker is :" + selectedVehicleTracker.getID());
        FacesMessage msg = new FacesMessage("VehicleTracker Selected", ((VehicleTracker) event.getObject()).getID());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onVehicleTrackerRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("VehicleTracker Unselected", ((VehicleTracker) event.getObject()).getID());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void linkCartrackerToCar() {
        try {
            vehicleService.setVehicleTracker(selectedCar.getID(), selectedVehicleTracker.getID());
            FacesMessage msg = new FacesMessage("VehicleTracker linked to Car");
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
