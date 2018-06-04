package service;

import classes.Employee;
import dao.IEmployeeDAO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;

@Stateless
public class EmployeeService implements Serializable {

    @Inject
    IEmployeeDAO employeeDAO;

    public EmployeeService() {}

    public void Load() {
        employeeDAO.Load();
    }

    public Employee getEmployeeByEmail(String name) {
        return employeeDAO.getEmployeeByEmail(name);
    }
}
