package com.message.rabbitMQ;

import com.message.queue.Subscriber;

import java.util.concurrent.CompletableFuture;

public class RabbitMQSubscriber<T> implements Subscriber<T> {

    @Override
    public CompletableFuture<T> receiver() {
        return null;
    }
}
