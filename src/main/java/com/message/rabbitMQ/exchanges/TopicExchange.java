package com.message.rabbitMQ.exchanges;

public class TopicExchange extends Exchange {

    private String routingPattern;

    public String getRoutingPattern() {
        return routingPattern;
    }

    public void setRoutingPattern(String routingPattern) {
        this.routingPattern = routingPattern;
    }
}
