package bean;

import domain.KilometerRate;
import domain.RateCategory;
import domain.Road;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private Date startDate;

    private Date newStartDate;
    private Double newKMRate;

    private Date format;
    private Date newCategorieStartDate;
    private Double newPercentagePrice;

    private final SimpleDateFormat datef = new SimpleDateFormat("dd/MM/yyyy");
    private RateCategory selectedCategorie;


    @PostConstruct
    public void init() {

    }

    public Road getSelectedRoad() {
        return selectedRoad;
    }

    public void setSelectedRoad(Road selectedRoad) {
        this.selectedRoad = selectedRoad;
    }

    public RateCategory getSelectedCategorie() {
        return selectedCategorie;
    }

    public void setSelectedCategorie(RateCategory selectedCategorie) {
        this.selectedCategorie = selectedCategorie;
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
        catch(IllegalArgumentException e)
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

    public void onCategorieRowSelect(SelectEvent event) {
        //TODO: Cast to Road Class
        this.selectedCategorie = ((RateCategory)event.getObject());
        FacesMessage msg = new FacesMessage("Road selected", this.selectedRoad.getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public void onRoadRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Road Unselected", ((Road) event.getObject()).getName());
        this.selectedRoad = null;
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void editRow(RowEditEvent event) throws SQLException {
        Road road = (Road) event.getObject();
        /*if (newKMRate != null && newStartDate != null && newStartDate.getTime() > selectedRoad.getKilometerRate().getStartDate().getTime()) {
            KilometerRate kilometerRate = rateService.editKilometerRate(selectedRoad.getKilometerRate().getId(), newKMRate, newStartDate);

            newKMRate = null;
            newStartDate = null;

            this.selectedRoad = kilometerRate.getRoad();
        }*/
        KilometerRate kilometerRate = rateService.editKilometerRate(road);
        this.selectedRoad = kilometerRate.getRoad();
    }

    public void onRowCancel(RowEditEvent event) {
        newKMRate = null;
        newStartDate = null;
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Kilometer rate edit canceled", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCategoryRowEdit(RowEditEvent event) throws SQLException {
        RateCategory category = (RateCategory) event.getObject();
        /*if (newPercentagePrice != null && newCategorieStartDate != null && newCategorieStartDate.getTime() > selectedCategorie.getStartDate().getTime()) {
            RateCategory rateCategorie = rateService.edit(selectedCategorie.getId(), newPercentagePrice, newCategorieStartDate);

            newPercentagePrice = null;
            newCategorieStartDate = null;

            this.selectedRoad = rateCategorie.getRoad();
        }*/
        RateCategory rateCategory = rateService.edit(category);
        this.selectedRoad = rateCategory.getRoad();
    }

    public void onCategoryRowCancel(RowEditEvent event) {
        newPercentagePrice = null;
        newCategorieStartDate = null;
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Rate category edit canceled", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRoadRateCellEdit(CellEditEvent event){
        String oldValue = event.getOldValue().toString();
        String newValue = event.getNewValue().toString();
    }

    /*public void onKilometerRateEdit(CellEditEvent event){
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
    }*/

    public void onRatecategoryCellEdit(CellEditEvent event){
        /*String oldValue = event.getOldValue().toString();
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
        }*/
    }

    public Double getNewKMRate() {
        if (newKMRate == null)
            newKMRate = selectedRoad.getKilometerRate().getKilometerPrice();
        return newKMRate;
    }

    public void setNewKMRate(Double value) {
        newKMRate = value;
    }

    public String getNewStartDate() {
        if (newStartDate == null)
            return datef.format(selectedRoad.getKilometerRate().getStartDate());
        return datef.format(newStartDate);
    }

    public void setNewStartDate(String newStartDate) {
        Date date = null;
        try {
            date = datef.parse(newStartDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.newStartDate = date;
    }

    public String getNewCategorieStartDate() {
        if (newCategorieStartDate != null)
            return datef.format(selectedCategorie.getStartDate());
        return datef.format(newCategorieStartDate);
    }

    public String getFormat() {
        if (format == null) {
            return "";
        }
        return datef.format(format);
    }

    public String ToFormat(Date date) {
        return datef.format(date);
    }

    public void setFormat(String format) {
        try {
            this.format = datef.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String setTestFormat(Date date) {
        this.format = date;
        return datef.format(this.format);
    }

    public void setNewCategorieStartDate(String newCategorieStartDate) {
        Date date = null;
        try {
            date = datef.parse(newCategorieStartDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.newCategorieStartDate = date;
    }

    public Double getNewPercentagePrice() {
        return newPercentagePrice;
    }

    public void setNewPercentagePrice(Double newPercentagePrice) {
        this.newPercentagePrice = newPercentagePrice;
    }
}
