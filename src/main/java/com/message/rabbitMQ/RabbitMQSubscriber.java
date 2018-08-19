package com.message.rabbitMQ;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.message.queue.Subscriber;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class RabbitMQSubscriber implements Subscriber {

    private RabbitMQClient rabbitMQClient;

    public RabbitMQSubscriber(RabbitMQClient rabbitMQClient) {
        this.rabbitMQClient = rabbitMQClient;
    }

    @Override
    public CompletableFuture<JsonNode> receiver(String queue) throws IOException {
        CompletableFuture<JsonNode> future = new CompletableFuture<>();
        Consumer consumer = new DefaultConsumer(rabbitMQClient.getChannel()) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" Message Received Queue 1 '" + message + "'");

                ObjectMapper mapper = new ObjectMapper();
                ObjectNode jsonNodes = mapper.createObjectNode();
                jsonNodes.put("body", body);
                jsonNodes.put("consumerTag", consumerTag);
                jsonNodes.putPOJO("properties", properties);
                future.complete(jsonNodes);
            }
        };
        rabbitMQClient.getChannel().basicConsume(queue, true, consumer);
        return future;
    }
}
