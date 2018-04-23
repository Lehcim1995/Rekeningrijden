package bean;

import classes.*;
import classes.RateCategoryEnum;
import classes.Vehicle;
import service.VehicleService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SessionScoped
@Named(value="carBean")
public class CarBean implements Serializable {

    @Inject
    private VehicleService vehicleService;

    private String licenseplate = "";
    private Date buildYear;
    private String rateCategorie = "";
    private String fueltype = "";
    private List<RateCategoryEnum> classificationList = Arrays.asList(RateCategoryEnum.values());
    private List<FuelEnum> fueltypes = Arrays.asList(FuelEnum.values());
    private int weight;


    @PostConstruct
    public void init(){
        this.licenseplate = null;
        this.buildYear = null;
        this.rateCategorie = null;
        this.fueltype = null;
    }

    public String getLicenseplate() {
        return licenseplate;
    }

    public void setLicenseplate(String licenseplate) {
        this.licenseplate = licenseplate;
    }

    public Date getBuildYear() {
        return buildYear;
    }

    public void setBuildYear(Date buildYear) {
        this.buildYear = buildYear;
    }

    public String getRateCategorie() {
        return rateCategorie;
    }

    public void setRateCategorie(String rateCategorie) {
        this.rateCategorie = rateCategorie;
    }

    public List<RateCategoryEnum> getClassificationList() {
        return classificationList;
    }

    public void setClassificationList(List<RateCategoryEnum> classificationList) {
        this.classificationList = classificationList;
    }

    public String getFueltype() {
        return fueltype;
    }

    public void setFueltype(String fueltype) {
        this.fueltype = fueltype;
    }

    public int getWeight() {
        return weight;
    }

    public List<FuelEnum> getFueltypes() {
        return fueltypes;
    }

    public void setFueltypes(List<FuelEnum> fueltypes) {
        this.fueltypes = fueltypes;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void valueChanged(ValueChangeEvent event){
        rateCategorie = event.getNewValue().toString();
    }
    public void fueltypeValueChanged(ValueChangeEvent event){
        fueltype = event.getNewValue().toString();
    }

    public void createCar(){
        try{
            if(rateCategorie != null && licenseplate != null && buildYear != null && fueltype != null)
            {
                Vehicle vehicle = vehicleService.createVehicleParam(this.rateCategorie, this.licenseplate , this.buildYear, this.weight, this.fueltype);
                if(vehicle != null)
                {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Car Added!", "Car with licenseplate " + this.licenseplate + " Added!"));
                }
                else{
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Please fill in all the required fields!"));
                }
            }
            else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Please fill in all the required fields!"));
            }
        }
        catch(Exception e)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "CreationException", "Could not create the car, please check all the fields for spellingerrors. The car may already exist."));
        }

    }

    public List<Vehicle> getAllCars() {
        return vehicleService.getVehicles();
    }
}
