package com.message.rabbitMQ;

public enum ExchangeType {

    DIRECT("direct"),
    TOPIC("topic"),
    FANOUT("fanout"),
    HEADER("headers");

    private final String type;

    ExchangeType(String type) {
        this.type = type;
    }

    public String type() {
        return this.type;
    }
}
