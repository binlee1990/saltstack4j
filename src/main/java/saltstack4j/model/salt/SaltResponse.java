package saltstack4j.model.salt;

import java.util.List;

import saltstack4j.model.linux.LinuxResponse;

public class SaltResponse extends LinuxResponse {

    private List<Step> steps;

    public SaltResponse() {
        super();
    }

    public SaltResponse(boolean status) {
        super(status);
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

}
