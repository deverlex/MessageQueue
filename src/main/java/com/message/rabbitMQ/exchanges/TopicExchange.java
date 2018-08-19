package com.message.rabbitMQ.exchanges;

public class TopicExchange extends Exchange {

    private String routingKey;
    private String routingPattern;

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public String getRoutingPattern() {
        return routingPattern;
    }

    public void setRoutingPattern(String routingPattern) {
        this.routingPattern = routingPattern;
    }
}
