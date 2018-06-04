package classes;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

@Entity
@XmlAccessorType
public class Employee implements Serializable {

    @Id
    @Column(unique = true)
    private String email;
    @XmlTransient
    @JsonIgnore
    private String password;
    /*@Enumerated(EnumType.STRING)
    private Role role;*/

    @ManyToOne
    @JoinTable(name = "employee_employeeGroup", joinColumns = @JoinColumn(name = "email", referencedColumnName = "email"),
            inverseJoinColumns = @JoinColumn(name = "groupName", referencedColumnName = "groupName"))
    @XmlTransient
    @JsonIgnore
    private EmployeeGroup group;

    public Employee() {}

    public Employee(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EmployeeGroup getGroup() {
        return group;
    }

    public void setGroup(EmployeeGroup group) {
        this.group = group;
    }
}
