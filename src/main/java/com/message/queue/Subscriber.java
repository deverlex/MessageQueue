package com.message.queue;

import com.message.rabbitMQ.SubscribeListener;

import java.io.IOException;

/**
 * @author Nguyen Van Do
 * @email nguyendo94vn@gmail.com
 */
public interface Subscriber<T> {

    void receive(String tag, SubscribeListener<T> listener) throws IOException;
}
