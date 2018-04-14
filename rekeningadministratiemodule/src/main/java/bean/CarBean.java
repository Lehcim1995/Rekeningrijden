package bean;

import classes.PaymentEnum;
import classes.RateCategory;
import service.VehicleService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RequestScoped
@Named(value="carBean")
public class CarBean implements Serializable {

    @Inject
    private VehicleService vehicleService;

    private String licenseplate = "";
    private Date buildYear;
    private String rateCategorie = "";
    private List<RateCategory> classificationList = Arrays.asList(RateCategory.values());

    @PostConstruct
    public void init(){
        this.licenseplate = null;
        this.buildYear = null;
        this.rateCategorie = null;
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

    public List<RateCategory> getClassificationList() {
        return classificationList;
    }

    public void setClassificationList(List<RateCategory> classificationList) {
        this.classificationList = classificationList;
    }

    public void valueChanged(ValueChangeEvent event){
        rateCategorie = event.getNewValue().toString();
    }

    public void createCar(){
        if(rateCategorie != null && licenseplate != null && buildYear != null)
        {
            vehicleService.createVehicleParam(this.rateCategorie, this.licenseplate , this.buildYear);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Car Added!", "Car with licenseplate " + this.licenseplate + " Added!"));
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Please fill in all the required fields!"));
        }
    }
}
