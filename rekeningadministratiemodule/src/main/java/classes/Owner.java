package classes;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Owner implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private int citizenId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String address;
    private String city;
    private String accountNumber;
    private String password;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Vehicle> vehicles;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(nullable = true)
    private List<Vehicle> previousVehicles;

    public Owner() {}

    public Owner(int citizenId, String firstName, String middleName, String lastName, String address, String city, String accountNumber, String password) {
        this.citizenId = citizenId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.accountNumber = accountNumber;
        this.password = password;
        vehicles = new ArrayList<>();
        previousVehicles = new ArrayList<>();
    }

    public Owner(int citizenId, String firstName, String middleName, String lastName, String address, String city) {
        this.citizenId = citizenId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        vehicles = new ArrayList<>();
        previousVehicles = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(int citizenId) {
        this.citizenId = citizenId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Vehicle> getPreviousVehicles() {
        return previousVehicles;
    }

    public void setPreviousVehicles(List<Vehicle> previousVehicles) {
        this.previousVehicles = previousVehicles;
    }

    public void add(Vehicle vehicle){
        vehicle.setOwner(this);
        this.vehicles.add(vehicle);
    }
    public void addPrevious(Vehicle vehicle){
        this.vehicles.remove(vehicle);
        this.previousVehicles.add(vehicle);
    }
}
