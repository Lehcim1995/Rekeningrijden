package classes;

import java.util.Date;

public class Checkpoint {

    private float longitude;
    private float lattitude;
    private Date time;

    public Checkpoint(float longitude, float lattitude, Date time) {
        this.longitude = longitude;
        this.lattitude = lattitude;
        this.time = time;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLattitude() {
        return lattitude;
    }

    public void setLattitude(float lattitude) {
        this.lattitude = lattitude;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
