package dao;

import classes.Employee;
import classes.EmployeeGroup;
import org.apache.commons.codec.digest.DigestUtils;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
public class EmployeeDAO implements IEmployeeDAO {

    @PersistenceContext(name = "accountAdministrationPU")
    private EntityManager em;
    private CriteriaBuilder cb;
    private CriteriaQuery<EmployeeGroup> cpg;
    private CriteriaQuery<Employee> cp;
    private Root<EmployeeGroup> employeeGroupRoot;
    private Root<Employee> employeeRoot;

    @PostConstruct
    public void init() {

        cb = em.getCriteriaBuilder();
        setupProfileGroupJPA();
        setupJPA();

        EmployeeGroup kmPrijs = new EmployeeGroup("KM_PRIJS");
        EmployeeGroup employee = new EmployeeGroup("EMPLOYEE");
        em.persist(kmPrijs);
        em.persist(employee);

        Employee Jasper = new Employee("jasper@taxandtracks.com", DigestUtils.sha256Hex("Jasper"));
        em.persist(Jasper);
        Jasper.setGroup(kmPrijs);
        em.merge(Jasper);

        Employee Stefano = new Employee("stefano@taxandtracks.com", DigestUtils.sha256Hex("Stefano"));
        em.persist(Stefano);
        Stefano.setGroup(employee);
        em.merge(Stefano);
    }

    @Override
    public void Load() {}

    @Override
    public Employee getEmployeeByEmail(String name) {
        return em.find(Employee.class, name);
    }

    public void setupJPA() {
        cp = cb.createQuery(Employee.class);
        employeeRoot = cp.from(Employee.class);
        cp.select(employeeRoot);
    }

    public void setupProfileGroupJPA() {
        cpg = cb.createQuery(EmployeeGroup.class);
        employeeGroupRoot = cpg.from(EmployeeGroup.class);
        cpg.select(employeeGroupRoot);
    }
}
