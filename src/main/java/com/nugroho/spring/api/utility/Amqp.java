package com.nugroho.spring.api.utility;

import java.util.Date;

import com.nugroho.spring.api.global.Config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Amqp {

    public static void sendMessage(RabbitTemplate rabbitTemplate, String purpose, String topic, String routing, Object message) {
        rabbitTemplate.convertAndSend(topic, routing, message, properties -> {
            properties.getMessageProperties().setDelay(0);
            properties.getMessageProperties().setHeader(Config.RABBIT_QUEUE_HEADER_TYPE,
                    Config.RABBIT_QUEUE_HEADER_TYPE_MESSAGE);
            properties.getMessageProperties().setHeader(Config.RABBIT_QUEUE_HEADER_PURPOSES, purpose);
            return properties;
        });

        log.info("success sent message...");
    }

    public static void sendMessage(RabbitTemplate rabbitTemplate, String purpose, String topic, String routing,
            Object message, Date runAt) {
        var countMilisecond = runAt.compareTo(new Date());
        rabbitTemplate.convertAndSend(topic, routing, message, properties -> {
            properties.getMessageProperties().setDelay(countMilisecond);
            properties.getMessageProperties().setHeader(Config.RABBIT_QUEUE_HEADER_TYPE,
                    Config.RABBIT_QUEUE_HEADER_TYPE_SCHEDULE);
            properties.getMessageProperties().setHeader(Config.RABBIT_QUEUE_HEADER_PURPOSES, purpose);
            return properties;
        });

        log.info("success sent message schedule...");
    }

}
