package saltstack4j.core.transport;

import saltstack4j.model.Response;

public class LinuxResponse implements Response {

    private boolean result;
    private String message;

    public LinuxResponse() {
        result = true;
        message = "";
    }

    public LinuxResponse(boolean status) {
        result = status;
        message = "";
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
