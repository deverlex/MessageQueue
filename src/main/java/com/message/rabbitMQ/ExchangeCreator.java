package com.message.rabbitMQ;

import com.message.rabbitMQ.exchanges.DirectExchange;
import com.message.rabbitMQ.exchanges.FanoutExchange;
import com.message.rabbitMQ.exchanges.TopicExchange;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;

import java.io.IOException;

/**
 * @author Nguyen Van Do
 * @email nguyendo94vn@gmail.com
 */
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

    public AMQP.Queue.BindOk createTopicExchange(TopicExchange topicExchange) {
        Channel channel = rabbitMQClient.getChannel();

        return null;
    }

    public AMQP.Queue.BindOk createFanoutExchange(FanoutExchange fanoutExchange) {
        Channel channel = rabbitMQClient.getChannel();

        return null;
    }
}
