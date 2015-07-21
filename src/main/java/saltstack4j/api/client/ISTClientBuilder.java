package saltstack4j.api.client;

import saltstack4j.api.STClient;

public interface ISTClientBuilder<R, T extends ISTClientBuilder<R, T>> {

    T credentials(String user, String password);

    T ip(String ip);

    R authenticate();

    public interface Client extends ISTClientBuilder<STClient, Client> {

        Client tenantName(String tenantName);

        Client tenantId(String tenantId);
    }
}
