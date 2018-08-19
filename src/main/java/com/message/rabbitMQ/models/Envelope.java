package com.message.rabbitMQ.models;

public class Envelope {

    private long deliveryTag;
    private boolean redeliver;
    private String exchange;
    private String routingKey;

    public Envelope() {

    }

    public Envelope(long deliveryTag, boolean redeliver, String exchange, String routingKey) {
        this.deliveryTag = deliveryTag;
        this.redeliver = redeliver;
        this.exchange = exchange;
        this.routingKey = routingKey;
    }

    public long getDeliveryTag() {
        return deliveryTag;
    }

    public void setDeliveryTag(long deliveryTag) {
        this.deliveryTag = deliveryTag;
    }

    public boolean isRedeliver() {
        return redeliver;
    }

    public void setRedeliver(boolean redeliver) {
        this.redeliver = redeliver;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    @Override public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Envelope(deliveryTag=").append(deliveryTag);
        sb.append(", redeliver=").append(redeliver);
        sb.append(", exchange=").append(exchange);
        sb.append(", routingKey=").append(routingKey);
        sb.append(")");
        return sb.toString();
    }
}
