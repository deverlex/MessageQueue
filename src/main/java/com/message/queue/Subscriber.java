package com.message.queue;

import java.util.concurrent.CompletableFuture;

/**
 * @author Nguyen Van Do
 * @email nguyendo94vn@gmail.com
 */
public interface Subscriber<T> {

    CompletableFuture<T> receiver();
}
