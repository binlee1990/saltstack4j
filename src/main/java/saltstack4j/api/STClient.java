package saltstack4j.api;

import saltstack4j.api.cmd.CmdService;
import saltstack4j.api.state.StateService;

public interface STClient {

    CmdService cmd();

    StateService state();
}
