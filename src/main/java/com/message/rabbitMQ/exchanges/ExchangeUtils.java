package com.message.rabbitMQ.exchanges;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ExchangeUtils {

    private static final Logger logger = LoggerFactory.getLogger(ExchangeUtils.class);

    public static boolean isExchangeNameExist(Channel channel, String exchangeName) {
        try {
            return channel.exchangeDeclarePassive(exchangeName) != null;
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("[{}] Exception when check exchange name: {}", exchangeName, e);
            return false;
        }
    }

    public static boolean isQueueExist(Channel channel, String queueName) {
        try {
            return channel.queueDeclarePassive(queueName) != null;
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("[{}] Exception when check exchange name: {}", queueName, e);
            return false;
        }
    }
}
