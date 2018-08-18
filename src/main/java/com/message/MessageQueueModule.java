package com.message;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.message.queue.Receiver;
import com.message.queue.Sender;
import com.message.rabbitMQ.RabbitMQReceiver;
import com.message.rabbitMQ.RabbitMQSender;

/**
 * @author Nguyen Van Do
 * @email nguyendo94vn@gmail.com
 */
public class MessageQueueModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Receiver.class).annotatedWith(Names.named(QueueType.RABBIT_MQ.type())).to(RabbitMQReceiver.class);
        bind(Sender.class).annotatedWith(Names.named(QueueType.RABBIT_MQ.type())).to(RabbitMQSender.class);
    }
}
