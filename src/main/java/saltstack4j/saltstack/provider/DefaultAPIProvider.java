package saltstack4j.saltstack.provider;

import java.util.Map;

import saltstack4j.api.APIProvider;
import saltstack4j.api.cmd.CmdService;
import saltstack4j.api.exceptions.ApiNotFoundException;
import saltstack4j.api.state.StateService;
import saltstack4j.saltstack.cmd.CmdServiceImpl;
import saltstack4j.saltstack.state.StateServiceImpl;

import com.google.common.collect.Maps;

public class DefaultAPIProvider implements APIProvider {

    private static final Map<Class<?>, Class<?>> bindings = Maps.newHashMap();
    private static final Map<Class<?>, Object> instances = Maps.newConcurrentMap();

    @Override
    public void initialize() {
        bind(CmdService.class, CmdServiceImpl.class);
        bind(StateService.class, StateServiceImpl.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(Class<T> api) {
        if (instances.containsKey(api))
            return (T) instances.get(api);

        if (bindings.containsKey(api)) {
            try {
                T impl = (T) bindings.get(api).newInstance();
                instances.put(api, impl);
                return impl;
            } catch (Exception e) {
                e.printStackTrace();
                throw new ApiNotFoundException("API Not found for: " + api.getName(), e);
            }
        }
        throw new ApiNotFoundException("API Not found for: " + api.getName());
    }

    private void bind(Class<?> api, Class<?> impl) {
        bindings.put(api, impl);
    }
}
