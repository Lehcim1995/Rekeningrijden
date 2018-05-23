package bean;

import domain.KilometerRate;
import domain.RateCategory;
import domain.Road;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import service.RateService;
import service.RoadService;

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
public class RateBean implements Serializable{

    @Inject
    RateService rateService;
    @Inject
    RoadService roadService;

    private Double kilometerprice ;
    //TODO: Change to Road DomainClass
    private Road selectedRoad;
    private RateCategory selectedRate;
    private Date startDate;


    @PostConstruct
    public void init() {

    }

    public Road getSelectedRoad() {
        return selectedRoad;
    }

    public void setSelectedRoad(Road selectedRoad) {
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
            FacesMessage msg = new FacesMessage("Something went wrong when getting al the rates " +
                    e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }

    }

    public List<Road> getAllRoads()
    {
        try{
            return roadService.getAllRoads();
        }
        catch(SQLException e)
        {
            FacesMessage msg = new FacesMessage("Something went wrong when getting al the Roads " +
                    e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }

    }

    public void onRoadRowSelect(SelectEvent event) {
        //TODO: Cast to Road Class
        this.selectedRoad = ((Road)event.getObject());
        FacesMessage msg = new FacesMessage("Road selected", this.selectedRoad.getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public void onRoadRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Road Unselected", ((Road) event.getObject()).getName());
        this.selectedRoad = null;
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onKilometerRateEdit(CellEditEvent event){
        String oldValue = event.getOldValue().toString();
        String newValue = event.getNewValue().toString();

        if(newValue != null && !newValue.equals(oldValue)) {
            try{
                Road entity = (Road) ((DataTable) event.getComponent()).getRowData();
                rateService.editKilometerRate(entity.getKilometerRate().getId(), Double.valueOf(newValue));
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell edited", "Old: " + oldValue + ", New:" + newValue);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
            catch(Exception e)
            {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Something went wrong while editing the cell", "error: " + e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    }

    public void onRatecategoryCellEdit(CellEditEvent event){
        String oldValue = event.getOldValue().toString();
        String newValue = event.getNewValue().toString();

        if(newValue != null && !newValue.equals(oldValue)) {
            try{
                Road entity = (Road) ((DataTable) event.getComponent()).getRowData();
                rateService.edit(entity.getKilometerRate().getRateCategoryEnum().getId(), Double.valueOf(newValue));

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
