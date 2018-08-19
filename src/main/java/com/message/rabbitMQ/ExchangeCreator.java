package com.message.rabbitMQ;

import com.message.rabbitMQ.ExchangeType;
import com.message.rabbitMQ.RabbitMQClient;
import com.message.rabbitMQ.exchanges.DirectExchange;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;

import java.io.IOException;

class ExchangeCreator {

    private RabbitMQClient rabbitMQClient;

    public ExchangeCreator(RabbitMQClient rabbitMQClient) {
        this.rabbitMQClient = rabbitMQClient;
    }

    public AMQP.Queue.BindOk createDirectExchange(DirectExchange directEx) throws IOException {
        Channel channel = rabbitMQClient.getChannel();
        channel.exchangeDeclare(directEx.getExchangeName(), ExchangeType.DIRECT.type(), directEx.isDurable());
        channel.queueDeclare(directEx.getQueueName(), directEx.isDurable(), directEx.isExclusive(),
                    directEx.isAutoDelete(), directEx.getQueueArgs());
        return channel.queueBind(directEx.getQueueName(), directEx.getExchangeName(),
                directEx.getRoutingKey(), directEx.getBindQueueArgs());
    }
}
