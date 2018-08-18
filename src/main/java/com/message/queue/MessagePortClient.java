package com.message.queue;

import com.message.error.BuilderException;
import com.message.utils.TextUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Nguyen Van Do
 * @email nguyendo94vn@gmail.com
 */
public final class MessagePortClient {

    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;

    private String username;
    private String password;
    private String host;
    private Integer port;
    private String virtualHost;

    private int connectionTimeout;
    private int handshakeTimeout;

    public static MessagePortClient CreatePort() {
        return new MessagePortClient();
    }

    private MessagePortClient() {
        this.factory = new ConnectionFactory();
        this.virtualHost = "/";
        // set timeout = 10s
        this.connectionTimeout = 10000;
        this.handshakeTimeout = 10000;
    }

    public MessagePortClient withUsername(String username) {
        this.username = username;
        return this;
    }

    public MessagePortClient withPassword(String password) {
        this.password = password;
        return this;
    }

    public MessagePortClient withHost(String host) {
        this.host = host;
        return this;
    }

    public MessagePortClient withTLS(boolean isTls) {
        port = isTls ? 5671: 5672;
        return this;
    }

    public MessagePortClient withVirtualHost(String virtualHost) {
        this.virtualHost = virtualHost;
        return this;
    }

    public MessagePortClient withConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
        return this;
    }

    public MessagePortClient withHandshakeTimeout(int handshakeTimeout) {
        this.handshakeTimeout= handshakeTimeout;
        return this;
    }

    public MessagePortClient build() throws BuilderException {
        if (TextUtils.isEmpty(username)) {
            throw new BuilderException("Missing username");
        }

        if (TextUtils.isEmpty(password)) {
            throw new BuilderException("Missing password");
        }

        if (TextUtils.isEmpty(host)) {
            throw new BuilderException("Missing host");
        }

        if (port == null) {
            throw new BuilderException("Missing port, please set check TLS");
        }

        factory.setUsername(username);
        factory.setPassword(password);
        factory.setHost(host);
        factory.setPort(port);
        factory.setVirtualHost(virtualHost);
        factory.setConnectionTimeout(connectionTimeout);
        factory.setHandshakeTimeout(handshakeTimeout);

        return this;
    }

    public void openConnection() throws IOException, TimeoutException {
        connection = factory.newConnection();
        channel = connection.createChannel();
    }

    public void closeConnection() throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }
}
