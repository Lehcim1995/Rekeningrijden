package bean;

import classes.KilometerRate;
import classes.RateCategory;
import javafx.scene.control.TableColumn;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import service.RateService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@SessionScoped
@Named(value = "rateBean")
public class RateRoadBean implements Serializable{


    @Inject
    RateService rateService;

    private Double kilometerprice ;
    //TODO: Change to Road DomainClass
    private String selectedRoad;
    private RateCategory selectedRate;
    private Date startDate;


    @PostConstruct
    public void init() {

    }

    public String getSelectedRoad() {
        return selectedRoad;
    }

    public void setSelectedRoad(String selectedRoad) {
        this.selectedRoad = selectedRoad;
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

    public RateCategory getSelectedRate() {
        return selectedRate;
    }

    public void setSelectedRate(RateCategory selectedRate) {
        this.selectedRate = selectedRate;
    }

    public List<KilometerRate> getAllRates()
    {
        try{
            return rateService.getAllKilometerRates();
        }
        catch(SQLException e)
        {
            FacesMessage msg = new FacesMessage("Something went wrong when getting al the Roads " +
                    e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }

    }

    public String getAllRoads()
    {
        //TODO: Service call getallrates
        return "ToImplement";
    }

    public void onRoadRowSelect(SelectEvent event) {
        //TODO: Cast to Road Class
        this.selectedRoad = ((KilometerRate)event.getObject()).getRoad();
        FacesMessage msg = new FacesMessage("Road selected", ((KilometerRate) event.getObject()).getRoad());
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public void onRoadRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Road Unselected", ((KilometerRate) event.getObject()).getRoad());
        this.selectedRoad = null;
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onpercentagePriceCellEdit(TableColumn.CellEditEvent event){
        String oldValue = event.getOldValue().toString();
        String newValue = event.getNewValue().toString();

        if(newValue != null && !newValue.equals(oldValue)) {
            try{
                //TODO: Servicecall editpercentageprice

                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell edited", "Old: " + oldValue + ", New:" + newValue);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
            catch(Exception e)
            {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Something went wrong while editing the cell", "Old: " + oldValue);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }

        }
    }
}
