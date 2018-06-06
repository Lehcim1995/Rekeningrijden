package initialize;

import exception.CouldNotCreateProfileException;
import service.RekeningAdministratieService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class InitProfiles {


    @Inject
    private RekeningAdministratieService rekeningAdminisntratieService;

    public InitProfiles() {

    }

    @PostConstruct
    public void initData() {

        try {
            rekeningAdminisntratieService.addProfile("Hans", "Admin", 253468129);
        } catch (CouldNotCreateProfileException e) {
            System.out.println("Error occured during creation of a profile see the stacktrace:");
            e.printStackTrace();
        }

    }
}
