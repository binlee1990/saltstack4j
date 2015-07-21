package saltstack4j.api.types;

public enum ServiceType {

    /**
	 */
    CMD("cmd.run"),
    /**
	 */
    STATE("state.sls"),
    /**
	 */
    UNKNOWN("NA");

    private final String command;

    ServiceType(String command) {
        this.command = command;
    }

    public String getCommand() {
        return this.command;
    }

    public static ServiceType forName(String name) {
        for (ServiceType s : ServiceType.values()) {
            if (s.getCommand().equalsIgnoreCase(name))
                return s;
        }
        return ServiceType.UNKNOWN;
    }
}
