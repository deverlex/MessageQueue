package demo;

import com.message.rabbitMQ.RabbitMQClient;
import com.message.rabbitMQ.RabbitMQPublisher;
import com.message.rabbitMQ.RabbitMQSubscriber;
import com.message.rabbitMQ.SubscribeListener;
import com.message.rabbitMQ.exchanges.DirectExchange;
import com.message.rabbitMQ.models.PublishMessage;
import com.message.utils.ObjectUtils;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

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

            for (int i = 0; i< 10; ++i) {
                Student student = new Student();
                student.setName("Spider man");
                message.setBody(ObjectUtils.serialize(student));
                CompletableFuture.supplyAsync(publisher.send(message)).get();
            }

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
            subscriber.receive("queue-direct-1", message -> {
                try {
                    Student student = (Student) ObjectUtils.deserialize(message.getBody());
                    System.out.println(student.getName());
                    System.out.println(student.getOld());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
