package saltstack4j.api;

import saltstack4j.api.cmd.CmdService;
import saltstack4j.api.state.StateService;

public interface SaltClient {

    CmdService cmd();

    StateService state();
}
