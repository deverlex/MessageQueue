package com.message.queue;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

/**
 * @author Nguyen Van Do
 * @email nguyendo94vn@gmail.com
 */
public interface Publisher<T> {

    Supplier<Boolean> send(T object);
}
