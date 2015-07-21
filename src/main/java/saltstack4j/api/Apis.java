package saltstack4j.api;

import java.util.ServiceLoader;

import saltstack4j.api.cmd.CmdService;
import saltstack4j.api.state.StateService;

public class Apis {

    private static final APIProvider provider = initializeProvider();

    public static <T> T get(Class<T> api) {
        return provider.get(api);
    }

    public static CmdService getCmdServices() {
        return get(CmdService.class);
    }

    public static StateService getStateServices() {
        return get(StateService.class);
    }

    private static APIProvider initializeProvider() {
        APIProvider p = ServiceLoader.load(APIProvider.class).iterator().next();
        p.initialize();
        return p;
    }
}
