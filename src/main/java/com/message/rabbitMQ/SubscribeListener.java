package com.message.rabbitMQ;

/**
 * @author Nguyen Van Do
 * @email nguyendo94vn@gmail.com
 */
public interface SubscribeListener<T> {

    void accepted(T message);
}
