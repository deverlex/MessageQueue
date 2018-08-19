package com.message.rabbitMQ.exchanges;

public class FanoutExchange extends Exchange {

    public String getRoutingKey() {
        return "";
    }
}
