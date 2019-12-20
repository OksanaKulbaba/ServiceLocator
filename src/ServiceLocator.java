import contex.InitialContext;
import service.Service;


public class ServiceLocator {
    private static Cache cache;

    static {
        cache = new Cache();
    }

    /**
     * First check the service object available in cache
     * If service object not available in cache do the lookup using
     * JNDI initial context and get the service object. Add it to
     * the cache for future use.
     *
     * @param jndiName The name of service object in context
     * @return Object mapped to name in context
     */
    public static Service getService(String jndiName) {
        Service cc = cache.getService(jndiName);
        if (cc!=null){
            return cc;
        }
        else {
           InitialContext context = new InitialContext();
            Service service = (Service)context.lookup(jndiName);
            cache.addService(service);
            return service;
        }

    }
}
