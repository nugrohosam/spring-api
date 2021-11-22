package com.nugroho.spring.api.presist.jobs.book.dto;

import com.google.gson.Gson;
import com.nugroho.spring.api.presist.jobs.Job;
import com.nugroho.spring.api.presist.jobs.book.BookReturnJobModel;
import com.nugroho.spring.api.utility.Amqp;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Date;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BookReturnJob implements Job {

    private RabbitTemplate rabbitTemplate;
    private String topic;
    private String routing;
    private String messageSend;
    private BookReturnJobModel data;
    private Gson gson;

    public BookReturnJob() {
        gson = new Gson();
    }

    public BookReturnJob(RabbitTemplate rabbitTemplate, String topic, String routing, String messageSend) {
        this.rabbitTemplate = rabbitTemplate;
        this.topic = topic;
        this.routing = routing;
        this.messageSend = messageSend;
    }

    public static final String PURPOSES = "BOOK_RETURN_JOB";

    public void setData(String messageFromRabbit) {
        data = gson.fromJson(messageFromRabbit, BookReturnJobModel.class);
    }

    @Override
    public void doJob() {
        log.info("do job : " + data.getMessage());
    }

    @Override
    public void sendJob(Date runAt) {
        log.info("send job with date");
        Amqp.sendMessage(rabbitTemplate, PURPOSES, topic, routing, messageSend, runAt);
    }

    @Override
    public void sendJob() {
        log.info("send job");
        Amqp.sendMessage(rabbitTemplate, PURPOSES, topic, routing, messageSend);
    }

}
