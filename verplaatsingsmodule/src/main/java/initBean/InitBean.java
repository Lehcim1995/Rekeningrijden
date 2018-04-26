package initBean;

import gateway.DisplacementReceiverGateway;
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
        return "Displacement system initialized";
    }
}
