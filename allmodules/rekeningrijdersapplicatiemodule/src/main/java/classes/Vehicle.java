package classes;

public class Vehicle {

    private String vehicleTrackerId;
    private String rateCategory;
    private String licensePlate;

    public Vehicle(String vehicleTrackerId, String rateCategory, String licensePlate) {
        this.vehicleTrackerId = vehicleTrackerId;
        this.rateCategory = rateCategory;
        this.licensePlate = licensePlate;
    }

    public String getVehicleTrackerId() {
        return vehicleTrackerId;
    }

    public void setVehicleTrackerId(String vehicleTrackerId) {
        this.vehicleTrackerId = vehicleTrackerId;
    }

    public String getRateCategory() {
        return rateCategory;
    }

    public void setRateCategory(String rateCategory) {
        this.rateCategory = rateCategory;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}
