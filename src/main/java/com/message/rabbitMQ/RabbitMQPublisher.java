package com.message.rabbitMQ;

import com.message.queue.Publisher;

import java.util.concurrent.CompletableFuture;

public class RabbitMQPublisher<T> implements Publisher<T> {

    @Override
    public CompletableFuture<T> send(T o) {

        return null;
    }
}
