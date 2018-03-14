package classes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DataObject
{

    private double stepLat;
    private double stepLon;
    private String id;

    public DataObject() {
    }

    public double getStepLat() {
        return stepLat;
    }

    public double getStepLon() {
        return stepLon;
    }

    public void setStepLat(double stepLat) {
        this.stepLat = stepLat;
    }

    public void setStepLon(double stepLon) {
        this.stepLon = stepLon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
