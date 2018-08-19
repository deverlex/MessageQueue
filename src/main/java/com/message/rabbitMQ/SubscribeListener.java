package com.message.rabbitMQ;

public interface SubscribeListener<T> {

    void accepted(T message);
}
