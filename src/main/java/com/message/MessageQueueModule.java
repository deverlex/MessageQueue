package com.message;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.message.queue.Subscriber;
import com.message.queue.Publisher;
import com.message.rabbitMQ.RabbitMQSubscriber;
import com.message.rabbitMQ.RabbitMQPublisher;

/**
 * @author Nguyen Van Do
 * @email nguyendo94vn@gmail.com
 */
public class MessageQueueModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Subscriber.class).annotatedWith(Names.named(QueueType.RABBIT_MQ.type())).to(RabbitMQSubscriber.class);
        bind(Publisher.class).annotatedWith(Names.named(QueueType.RABBIT_MQ.type())).to(RabbitMQPublisher.class);
    }
}
