package com.message.rabbitMQ;

/**
 * @author Nguyen Van Do
 * @email nguyendo94vn@gmail.com
 */
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
