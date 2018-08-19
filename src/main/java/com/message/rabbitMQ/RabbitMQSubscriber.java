package com.message.rabbitMQ;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

                com.message.rabbitMQ.models.Envelope env = new com.message.rabbitMQ.models.Envelope();
                env.setDeliveryTag(envelope.getDeliveryTag());
                env.setExchange(envelope.getExchange());
                env.setRedeliver(envelope.isRedeliver());
                env.setRoutingKey(envelope.getRoutingKey());

                com.message.rabbitMQ.models.BasicProperties props = new com.message.rabbitMQ.models.BasicProperties();
                props.setAppId(properties.getAppId());
                props.setClusterId(properties.getClusterId());
                props.setContentEncoding(properties.getContentEncoding());
                props.setContentType(properties.getContentType());
                props.setCorrelationId(properties.getCorrelationId());
                props.setDeliveryMode(properties.getDeliveryMode());
                props.setExpiration(properties.getExpiration());
                props.setUserId(properties.getUserId());
                props.setHeaders(properties.getHeaders());
                props.setPriority(properties.getPriority());
                props.setMessageId(properties.getMessageId());
                props.setTimestamp(properties.getTimestamp());
                props.setType(properties.getType());
                props.setReplyTo(properties.getReplyTo());

                SubscribeMessage message = new SubscribeMessage(consumerTag, env, props, body);
                future.complete(mapper.convertValue(message, JsonNode.class));
            }
        };
        rabbitMQClient.getChannel().basicConsume(tag, true, consumer);
        return future;
    }
}
