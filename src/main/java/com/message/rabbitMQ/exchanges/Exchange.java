package com.message.rabbitMQ.exchanges;

import java.util.Map;

public abstract class Exchange {

    private String exchangeName;
    private String queueName;
    private boolean durable;
    private boolean exclusive;
    private boolean autoDelete;
    private Map<String, Object> queueArgs;
    private Map<String, Object> bindQueueArgs;

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public boolean isDurable() {
        return durable;
    }

    public void setDurable(boolean durable) {
        this.durable = durable;
    }

    public boolean isExclusive() {
        return exclusive;
    }

    public void setExclusive(boolean exclusive) {
        this.exclusive = exclusive;
    }

    public boolean isAutoDelete() {
        return autoDelete;
    }

    public void setAutoDelete(boolean autoDelete) {
        this.autoDelete = autoDelete;
    }

    public Map<String, Object> getQueueArgs() {
        return queueArgs;
    }

    public void setQueueArgs(Map<String, Object> queueArgs) {
        this.queueArgs = queueArgs;
    }

    public Map<String, Object> getBindQueueArgs() {
        return bindQueueArgs;
    }

    public void setBindQueueArgs(Map<String, Object> bindQueueArgs) {
        this.bindQueueArgs = bindQueueArgs;
    }
}
