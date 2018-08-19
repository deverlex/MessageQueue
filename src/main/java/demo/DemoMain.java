package demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.message.rabbitMQ.RabbitMQClient;
import com.message.rabbitMQ.RabbitMQPublisher;
import com.message.rabbitMQ.RabbitMQSubscriber;
import com.message.rabbitMQ.exchanges.DirectExchange;
import com.message.rabbitMQ.models.PublishMessage;
import com.message.rabbitMQ.models.SubscribeMessage;

import java.util.function.BiConsumer;

public class DemoMain {

    public static void main(String[] args) {
        createSender();
        createReceiver();
    }

    public static void createSender() {
        try {
            RabbitMQClient rabbitMQClient = RabbitMQClient.CreateConnection("sender")
                    .withHost("192.168.100.21")
                    .withPort(5672)
                    .withUsername("mqadmin")
                    .withPassword("mqadminpassword").build();
            rabbitMQClient.openConnection();

            // demo in here
            DirectExchange directExchange = new DirectExchange();
            directExchange.setDurable(true);
            directExchange.setRoutingKey("direct-1");
            directExchange.setQueueName("queue-direct-1");
            directExchange.setExchangeName("exchange-1");

            RabbitMQPublisher publisher = new RabbitMQPublisher(rabbitMQClient, directExchange);
            PublishMessage message = new PublishMessage();
            message.setExchange("exchange-1");
            message.setRoutingKey("direct-1");
            message.setBody("I'm spiderman".getBytes());
            publisher.send(message).get();

            rabbitMQClient.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createReceiver() {
        try {
            RabbitMQClient rabbitMQClient = RabbitMQClient.CreateConnection("receiver")
                    .withHost("192.168.100.21")
                    .withPort(5672)
                    .withUsername("mqadmin")
                    .withPassword("mqadminpassword").build();
            rabbitMQClient.openConnection();

            // demo in here
            RabbitMQSubscriber subscriber = new RabbitMQSubscriber(rabbitMQClient);
            subscriber.receive("queue-direct-1").whenComplete(new BiConsumer<JsonNode, Throwable>() {
                @Override
                public void accept(JsonNode jsonNode, Throwable throwable) {
                    try {
                        ObjectMapper mapper = new ObjectMapper();
                        SubscribeMessage message = mapper.treeToValue(jsonNode, SubscribeMessage.class);
                        System.out.println(new String(message.getBody()));
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                }
            });

            rabbitMQClient.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
