package bean;

import Exceptions.CreationException;
import domain.VehicleTracker;
import service.VehicleService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@RequestScoped
@Named(value="cartrackerBean")
public class CartrackerBean implements Serializable {

    @Inject
    private VehicleService vehicleService;

    private String id = "";
    private String manufacturer = "";


    @PostConstruct
    public void init(){
        this.manufacturer = null;
        this.id = null;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void createCartracker(){
        try
        {
            VehicleTracker vehicleTracker = vehicleService.createVehicleTrackerId(this.id, this.manufacturer);
            if(vehicleTracker != null){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Car Added!", "Cartracker with id " + this.id + " Added!"));

            }
            else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Please fill in all the required fields!"));
            }
        }
        catch(CreationException e)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "CreationException", "Could not create the Cartracker, please check all the fields for spellingerrors. The Vehicletracker may already exist."));
        }
    }
  
    public List<VehicleTracker> getAllCarTrackers() {
        return vehicleService.getVehicleTrackers();
    }
}
