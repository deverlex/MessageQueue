package com.message.rabbitMQ;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.message.queue.Publisher;
import com.message.rabbitMQ.models.PublishMessage;
import com.rabbitmq.client.Channel;

import java.util.function.Supplier;

public class RabbitMQPublisher implements Publisher<PublishMessage> {

    private RabbitMQClient rabbitMQClient;

    public RabbitMQPublisher(RabbitMQClient rabbitMQClient) {
        this.rabbitMQClient = rabbitMQClient;
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
