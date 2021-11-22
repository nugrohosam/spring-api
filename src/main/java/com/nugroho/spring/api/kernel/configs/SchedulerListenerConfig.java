package com.nugroho.spring.api.kernel.configs;

import com.nugroho.spring.api.kernel.configs.jobs.JobListenerAdapterConfig;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity(debug = false)
@Slf4j
public class SchedulerListenerConfig {

    @Value("${spring.rabbitmq.queueCategory}")
    private String queueCategory;

    @Value("${spring.rabbitmq.durable}")
    private boolean durableAmqp;

    @Value("${spring.rabbitmq.queueCategory}")
    private String queueCategoryAmqp;

    @Value("${spring.rabbitmq.topicExchange}")
    private String topicExchangeAmqp;

    @Value("${spring.rabbitmq.connection-timeout}")
    private Integer timeoutAmqp;

    @Bean
    Queue queue() {
        return new Queue(queueCategoryAmqp, durableAmqp);
    }

    @Bean
    TopicExchange exchange() {
        var exchange = new TopicExchange(topicExchangeAmqp);
        exchange.setDelayed(true);
        return exchange;
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("#");
    }

    @Bean
    JobListenerAdapterConfig listenerAdapter() {
        return new JobListenerAdapterConfig();
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
            MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueCategoryAmqp);
        container.setConsumerStartTimeout(timeoutAmqp);
        container.setMessageListener(listenerAdapter);
        log.info("created container");
        return container;
    }

}
