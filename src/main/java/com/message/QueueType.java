package com.message;

/**
 * @author Nguyen Van Do
 * @email nguyendo94vn@gmail.com
 */
public enum QueueType {

    RABBIT_MQ("rabbitMQ");

    private String type;

    QueueType(String type) {
        this.type = type;
    }

    public String type() {
        return type;
    }
}
