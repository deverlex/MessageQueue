package com.message.rabbitMQ;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.message.queue.Publisher;
import com.message.rabbitMQ.exchanges.DirectExchange;
import com.message.rabbitMQ.exchanges.Exchange;
import com.message.rabbitMQ.exchanges.FanoutExchange;
import com.message.rabbitMQ.exchanges.TopicExchange;
import com.message.rabbitMQ.models.PublishMessage;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.function.Supplier;

public class RabbitMQPublisher implements Publisher<PublishMessage> {

    private RabbitMQClient rabbitMQClient;
    private ExchangeCreator exchangeCreator;

    public RabbitMQPublisher(RabbitMQClient rabbitMQClient, Exchange exchange) throws IOException {
        this.rabbitMQClient = rabbitMQClient;
        this.exchangeCreator = new ExchangeCreator(rabbitMQClient);

        if (exchange instanceof DirectExchange) {
            this.exchangeCreator.createDirectExchange((DirectExchange) exchange);
        } else if (exchange instanceof TopicExchange) {

        } else if (exchange instanceof FanoutExchange) {

        }
    }

    @Override
    public Supplier<Boolean> send(PublishMessage message) {
        return () -> {
            Channel channel = rabbitMQClient.getChannel();
            try {
                channel.basicPublish(
                        message.getExchange(), message.getRoutingKey(), message.isMandatory(),
                        message.getBasicProperties(), message.getBody()
                );
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        };
    }
}
