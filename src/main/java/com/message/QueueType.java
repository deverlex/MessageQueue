package com.message;

/**
 * @author Nguyen Van Do
 * @email nguyendo94vn@gmail.com
 */
public enum QueueType {

    RABBIT_MQ(Named.RABBIT_MQ);

    private String type;

    QueueType(String type) {
        this.type = type;
    }

    public String type() {
        return type;
    }

    public static class Named {
        public static final String RABBIT_MQ = "rabbitMQ";
    }
}
