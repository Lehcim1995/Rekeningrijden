package rest;

import service.RekeningAdministratieService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/rest")
public class REstApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> setClass = new HashSet<>();
        setClass.add(CORSFilter.class);
        setClass.add(RekeningAdministratieEndpoint.class);
        return setClass;
    }
}
