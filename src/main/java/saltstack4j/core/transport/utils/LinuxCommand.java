package saltstack4j.core.transport.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import saltstack4j.core.transport.LinuxResponse;
import saltstack4j.core.transport.enums.ChannelType;

import com.google.common.base.Charsets;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;

public class LinuxCommand {
    private LinuxChannel linuxChannel;
    private Charset charset = Charsets.UTF_8;

    public LinuxCommand(String ip, int port, String user, String password) {
        linuxChannel = new LinuxChannel(ip, port, user, password);
    }

    public LinuxCommand(String ip, String user, String password) {
        linuxChannel = new LinuxChannel(ip, user, password);
    }

    public LinuxCommand(String ip, int port, String user, boolean usePublicKey) {
        linuxChannel = new LinuxChannel(ip, port, user, usePublicKey);
    }

    public LinuxCommand(String ip, String user, boolean usePublicKey) {
        linuxChannel = new LinuxChannel(ip, user, usePublicKey);
    }

    public LinuxResponse runCmd(String cmd) throws JSchException, IOException {
        LinuxResponse response = null;

        Channel channel = null;
        BufferedReader reader = null;
        try {
            channel = getCmdChannel(cmd);
            reader = getReader(channel);
            response = getLinuxResponse(channel, reader);
        } finally {
            if (null != reader) {
                reader.close();
            }
            if (null != channel) {
                channel.disconnect();
            }
        }

        return response;
    }

    private LinuxResponse getLinuxResponse(Channel channel, BufferedReader reader) throws IOException {
        LinuxResponse response = new LinuxResponse();
        if (null == channel || null == reader) {
            response.setResult(false);
            return response;
        }

        int exitCode = 0;
        StringBuilder sb = new StringBuilder();
        while (true) {
            while (reader.ready()) {
                String buf = null;
                while ((buf = reader.readLine()) != null) {
                    sb.append(buf).append("\n");
                }
            }
            if (channel.isClosed()) {
                exitCode = channel.getExitStatus();
                break;
            }
        }

        response.setResult(exitCode == 0 ? true : false);
        response.setMessage(sb.toString());

        return response;
    }

    private BufferedReader getReader(Channel channel) throws IOException {
        InputStream in = channel.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, charset));
        return reader;
    }

    private Channel getCmdChannel(String cmd) throws JSchException {
        Channel channel = linuxChannel.getChannel(ChannelType.EXEC);
        ((ChannelExec) channel).setCommand(cmd);
        channel.connect();
        return channel;
    }

    public void closeSession() {
        if (null != linuxChannel) {
            linuxChannel.closeSession();
        }
    }
}