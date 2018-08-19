package com.message.rabbitMQ.models;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Envelope;

public class SubscribeMessage {

    private String consumerTag;
    private Envelope envelope;
    private AMQP.BasicProperties properties;
    private byte[] body;

    public SubscribeMessage(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
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

    public AMQP.BasicProperties getProperties() {
        return properties;
    }

    public byte[] getBody() {
        return body;
    }
}
