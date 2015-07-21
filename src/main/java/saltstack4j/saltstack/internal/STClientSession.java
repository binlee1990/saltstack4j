package saltstack4j.saltstack.internal;

import saltstack4j.api.Apis;
import saltstack4j.api.STClient;
import saltstack4j.api.cmd.CmdService;
import saltstack4j.api.state.StateService;

public class STClientSession implements STClient {

    @Override
    public CmdService cmd() {
        return Apis.getCmdServices();
    }

    @Override
    public StateService state() {
        return Apis.getStateServices();
    }

}
