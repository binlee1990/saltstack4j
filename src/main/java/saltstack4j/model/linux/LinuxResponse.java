package saltstack4j.model.linux;

import saltstack4j.model.Response;

public class LinuxResponse implements Response {

    private boolean success;
    private String message;

    public LinuxResponse() {
        success = true;
        message = "";
    }

    public LinuxResponse(boolean status) {
        success = status;
        message = "";
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
