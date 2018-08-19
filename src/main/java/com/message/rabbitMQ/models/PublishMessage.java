package com.message.rabbitMQ.models;

import com.rabbitmq.client.AMQP;

public class PublishMessage {

    private String exchange;
    private String routingKey;
    private boolean mandatory;
    private AMQP.BasicProperties basicProperties;
    private byte[] body;

    public PublishMessage() {

    }

    public PublishMessage(String exchange, String routingKey, byte[] body) {
        this.exchange = exchange;
        this.routingKey = routingKey;
        this.body = body;
    }

    public PublishMessage(String exchange, String routingKey, boolean mandatory, AMQP.BasicProperties basicProperties, byte[] body) {
        this.exchange = exchange;
        this.routingKey = routingKey;
        this.mandatory = mandatory;
        this.basicProperties = basicProperties;
        this.body = body;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public AMQP.BasicProperties getBasicProperties() {
        return basicProperties;
    }

    public void setBasicProperties(AMQP.BasicProperties basicProperties) {
        this.basicProperties = basicProperties;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }
}
