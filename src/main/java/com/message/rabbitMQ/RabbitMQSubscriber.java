package com.message.rabbitMQ;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.message.queue.Subscriber;
import com.message.rabbitMQ.models.SubscribeMessage;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class RabbitMQSubscriber implements Subscriber<JsonNode> {

    private RabbitMQClient rabbitMQClient;

    public RabbitMQSubscriber(RabbitMQClient rabbitMQClient) {
        this.rabbitMQClient = rabbitMQClient;
    }

    @Override
    public CompletableFuture<JsonNode> receive(String tag) throws IOException {
        CompletableFuture<JsonNode> future = new CompletableFuture<>();
        Consumer consumer = new DefaultConsumer(rabbitMQClient.getChannel()) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                ObjectMapper mapper = new ObjectMapper();
                SubscribeMessage message = new SubscribeMessage(consumerTag, envelope, properties, body);
                future.complete(mapper.convertValue(message, JsonNode.class));
            }
        };
        rabbitMQClient.getChannel().basicConsume(tag, true, consumer);
        return future;
    }
}
