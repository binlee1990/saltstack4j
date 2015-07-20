package saltstack4j.core.transport.enums;

public enum ChannelType {

    SFTP("sftp"), EXEC("exec");

    private String channelType;

    ChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getChannelType() {
        return channelType;
    }

}
