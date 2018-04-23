package bean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named(value = "rateBean")
public class RateBean {



    @PostConstruct
    public void init() {

    }

    public String getAllRates()
    {
        //TODO: Service call getallrates
        return "ToImplement";
    }

    public String getAllRoads()
    {
        //TODO: Service call getallrates
        return "ToImplement";
    }


}
