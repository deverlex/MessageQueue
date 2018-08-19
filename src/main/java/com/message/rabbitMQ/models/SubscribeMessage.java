package com.message.rabbitMQ.models;

public class SubscribeMessage {

    private String consumerTag;
    private Envelope envelope;
    private BasicProperties properties;
    private byte[] body;

    public SubscribeMessage() {

    }

    public SubscribeMessage(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body) {
        this.consumerTag = consumerTag;
        this.envelope = envelope;
        this.properties = properties;
        this.body = body;
    }

    public String getConsumerTag() {
        return consumerTag;
    }

    public Envelope getEnvelope() {
        return envelope;
    }

    public BasicProperties getProperties() {
        return properties;
    }

    public byte[] getBody() {
        return body;
    }
}
