package dao;

import classes.Employee;

public interface IEmployeeDAO {

    void Load();

    Employee getEmployeeByEmail(String name);
}
