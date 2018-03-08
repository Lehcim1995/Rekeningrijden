package classes;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;

@ApplicationPath("/rest")
public class VerplaatsingsEvent extends Application
{
    @Override
    public Set<Class<?>> getClasses()
    {
        // TODO add rest classes here
        return super.getClasses();
    }
}
