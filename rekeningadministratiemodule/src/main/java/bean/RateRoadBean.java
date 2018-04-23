package bean;

import classes.Vehicle;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

@SessionScoped
@Named(value = "rateBean")
public class RateRoadBean implements Serializable{

    private Double kilometerprice ;
    //TODO: Change to Road DomainClass
    //private KilometerRate selectedRoad;
    private Date startDate;


    @PostConstruct
    public void init() {

    }

    public String getSelectedRoad() {
        return /*selectedRoad*/"";
    }

    public void setSelectedRoad(String selectedRoad) {
        //this.selectedRoad = selectedRoad;
    }

    public Double getKilometerprice() {
        return kilometerprice;
    }

    public void setKilometerprice(Double kilometerprice) {
        this.kilometerprice = kilometerprice;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getAllRates()
    {
        //TODO: Service call getallrates
        return "ToImplement";
    }

    public String getAllRoads()
    {
        //TODO: Service call getallrates
        return "ToImplement";
    }

    public void onRoadRowSelect(SelectEvent event) {
        //TODO: Cast to Road Class
        /*this.selectedRoad = (KilometerRate) event.getObject();
        FacesMessage msg = new FacesMessage("Road selected", ((KilometerRate) event.getObject()).getRoad());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        */
    }

    public void onRoadRowUnselect(UnselectEvent event) {
        /*FacesMessage msg = new FacesMessage("Road Unselected", ((KilometerRate) event.getObject()).getRoad());
        this.selectedRoad = null;
        FacesContext.getCurrentInstance().addMessage(null, msg);*/
    }
}
