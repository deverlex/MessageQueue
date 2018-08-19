package com.message.rabbitMQ.exchanges;

public class DirectExchange extends Exchange {

    private String routingKey;

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }
}
