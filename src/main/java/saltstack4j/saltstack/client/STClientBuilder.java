package saltstack4j.saltstack.client;

import saltstack4j.api.client.ISTClientBuilder;

public abstract class STClientBuilder<R, T extends ISTClientBuilder<R, T>> implements ISTClientBuilder<R, T> {

    String ip;
    String user;
    String password;

    @SuppressWarnings("unchecked")
    @Override
    public T ip(String ip) {
        this.ip = ip;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T credentials(String user, String password) {
        this.user = user;
        this.password = password;
        return (T) this;
    }

}
