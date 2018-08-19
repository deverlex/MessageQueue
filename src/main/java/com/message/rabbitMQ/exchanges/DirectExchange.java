package com.message.rabbitMQ.exchanges;

import com.message.rabbitMQ.ExchangeType;
import com.message.rabbitMQ.RabbitMQClient;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;


public class DirectExchange extends Exchange {

    private String routingKey;

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }
}
