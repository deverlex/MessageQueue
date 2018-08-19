package com.message.queue;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

/**
 * @author Nguyen Van Do
 * @email nguyendo94vn@gmail.com
 */
public interface Subscriber<T> {

    CompletableFuture<T> receive(String tag) throws IOException;
}
