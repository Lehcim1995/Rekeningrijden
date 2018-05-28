package domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Road implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    @ElementCollection
    private List<String> geocodedAdresses;
    @OneToOne
    private KilometerRate kilometerRate;
    @OneToMany
    private List<KilometerRate> previousRates;
    private Date date;


    public Road(String name, List<String> geocodedAdresses, KilometerRate kilometerRate) {
        this.name = name;
        this.geocodedAdresses = geocodedAdresses;
        this.kilometerRate = kilometerRate;
        this.previousRates = new ArrayList<>();
        this.date = new Date();
    }

    public Road() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getGeocodedAdresses() {
        return geocodedAdresses;
    }

    public void setGeocodedAdresses(List<String> geocodedAdresses) {
        this.geocodedAdresses = geocodedAdresses;
    }

    public KilometerRate getKilometerRate() {
        return kilometerRate;
    }

    public void setKilometerRate(KilometerRate kilometerRate) {
        this.kilometerRate = kilometerRate;
    }

    public List<KilometerRate> getPreviousRates() {
        return previousRates;
    }

    public void setPreviousRates(List<KilometerRate> previousRates) {
        this.previousRates = previousRates;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
