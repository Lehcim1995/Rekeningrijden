package bean;

import classes.Employee;
import service.EmployeeService;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.security.Principal;

@Named
@SessionScoped
public class AuthenticationBean implements Serializable {

    @Inject
    private EmployeeService employeeService;

    private String email;
    private String password;
    private Employee employee;

    public String login() {

        // Preload database
        employeeService.Load();

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            request.login(email, password);

            Principal principal = request.getUserPrincipal();
            employee = employeeService.getEmployeeByEmail(principal.getName());

            //administrator = profileService.AuthenticateAdmin(email, password);
            if (request.isUserInRole("Moderator") || request.isUserInRole("Administrator")) return "admin/administrator.xhtml?faces-redirect=true";
            else {
                ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
                return "error/invalidAccount.xhtml?faces-redirect=true";
            }
        } catch (ServletException e) {
            System.out.println(e.getStackTrace().toString());
            ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
            return "error/invalidAccount.xhtml?faces-redirect=true";
        }
    }

    public String logout() {

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            employee = null;
            request.logout();
            ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
            return "/login.html?faces-redirect=true";
        } catch (ServletException e) {
            e.printStackTrace();
            ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
            return "/login.html?faces-redirect=true";
        }
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

    public Employee getEmployee() {
        return employee;
    }
}
