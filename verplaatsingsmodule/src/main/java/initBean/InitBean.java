package initBean;

import services.VerplaatsingsService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class InitBean {

    @Inject
    private VerplaatsingsService service;

    public String isInitialized() {
        service.init();
        return "Displacement system initialized";
    }
}
