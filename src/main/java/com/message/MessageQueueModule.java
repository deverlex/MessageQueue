package com.message;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.message.queue.ConnectionQueue;
import com.message.queue.Consumer;
import com.message.queue.Producer;
import com.message.rabbitMQ.RabbitMQConnectionQueue;
import com.message.rabbitMQ.RabbitMQConsumer;
import com.message.rabbitMQ.RabbitMQProducer;

/**
 * @author Nguyen Van Do
 * @email nguyendo94vn@gmail.com
 */
public class MessageQueueModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ConnectionQueue.class).annotatedWith(Names.named(QueueType.RABBIT_MQ.type())).to(RabbitMQConnectionQueue.class);

        bind(Consumer.class).annotatedWith(Names.named(QueueType.RABBIT_MQ.type())).to(RabbitMQConsumer.class);
        bind(Producer.class).annotatedWith(Names.named(QueueType.RABBIT_MQ.type())).to(RabbitMQProducer.class);
    }
}
