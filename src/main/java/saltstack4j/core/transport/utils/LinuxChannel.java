package saltstack4j.core.transport.utils;

import java.util.Properties;

import saltstack4j.core.transport.enums.ChannelType;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class LinuxChannel {

    /**
     * When you first login, the console will ask if you confirm. You can choose the value below that the console will
     * ask or not. (ask | yes | no)
     */
    private static final String HOSTKEY_CHECKING = "StrictHostKeyChecking";

    private String ip;
    private int port = 22;
    private String user;
    private String password;
    private boolean usePublicKey;

    private Session session;
    private Channel channel;

    public LinuxChannel(String ip, int port, String user, String password) {
        this.ip = ip;
        this.port = port;
        this.user = user;
        this.password = password;
    }

    public LinuxChannel(String ip, String user, String password) {
        this.ip = ip;
        this.user = user;
        this.password = password;
    }

    public LinuxChannel(String ip, int port, String user, boolean usePublicKey) {
        this.ip = ip;
        this.port = port;
        this.user = user;
        this.usePublicKey = usePublicKey;
    }

    public LinuxChannel(String ip, String user, boolean usePublicKey) {
        this.ip = ip;
        this.user = user;
        this.usePublicKey = usePublicKey;
    }

    public Channel getChannel(ChannelType channelType) throws JSchException {
        if (null == session) {
            openSession();
        }

        channel = session.openChannel(channelType.getChannelType());
        return channel;
    }

    public void closeSession() {
        if (channel != null) {
            channel.disconnect();
        }
        if (session != null) {
            session.disconnect();
        }
    }

    private void openSession() throws JSchException {
        JSch jsch = new JSch();
        session = jsch.getSession(user, ip, port);

        if (usePublicKey) {
            jsch.addIdentity("/home/jbossuser/.ssh/id_rsa");
        } else {
            session.setPassword(password);
        }

        Properties config = new Properties();
        config.put(HOSTKEY_CHECKING, "no");
        session.setConfig(config);
        session.connect();
    }
}
