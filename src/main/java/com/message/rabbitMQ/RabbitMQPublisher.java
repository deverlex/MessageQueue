package com.message.rabbitMQ;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.message.queue.Publisher;
import com.message.rabbitMQ.models.PublishMessage;
import com.rabbitmq.client.Channel;

import java.util.function.Supplier;

public class RabbitMQPublisher implements Publisher {

    private RabbitMQClient rabbitMQClient;

    public RabbitMQPublisher(RabbitMQClient rabbitMQClient) {
        this.rabbitMQClient = rabbitMQClient;
    }

    @Override
    public Supplier<Boolean> send(JsonNode object) {
        return () -> {
            Channel channel = rabbitMQClient.getChannel();
            try {
                ObjectMapper mapper = new ObjectMapper();
                PublishMessage message = mapper.treeToValue(object, PublishMessage.class);
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
