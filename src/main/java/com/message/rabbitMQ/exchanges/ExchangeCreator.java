package com.message.rabbitMQ.exchanges;

import com.message.rabbitMQ.ExchangeType;
import com.message.rabbitMQ.RabbitMQClient;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.Map;

public class ExchangeCreator {

    private RabbitMQClient rabbitMQClient;

    public ExchangeCreator(RabbitMQClient rabbitMQClient) {
        this.rabbitMQClient = rabbitMQClient;
    }

    public AMQP.Queue.BindOk createDirectExchange(DirectExchange directEx) throws IOException {
        Channel channel = rabbitMQClient.getChannel();
        boolean exchangeExist = ExchangeUtils.isExchangeNameExist(channel, directEx.getExchangeName());
        if (!exchangeExist) {
            channel.exchangeDeclare(directEx.getExchangeName(), ExchangeType.DIRECT.type(), directEx.isDurable());
        }

        boolean queueExist = ExchangeUtils.isQueueExist(channel, directEx.getQueueName());
        if (!queueExist) {
            channel.queueDeclare(directEx.getQueueName(), directEx.isDurable(), directEx.isExclusive(),
                    directEx.isAutoDelete(), directEx.getQueueArgs());
        }

        return channel.queueBind(directEx.getQueueName(), directEx.getExchangeName(),
                directEx.getRoutingKey(), directEx.getBindQueueArgs());
    }
}
