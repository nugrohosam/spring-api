package com.nugroho.spring.api.applications.controllers.v1;

import org.springframework.web.bind.annotation.RestController;

import com.nugroho.spring.api.global.Routes;
import com.nugroho.spring.api.presist.jobs.book.BookReturnJobModel;
import com.nugroho.spring.api.presist.jobs.book.dto.BookReturnJob;
import com.google.gson.Gson;
import com.nugroho.spring.api.global.Middleware;
import com.nugroho.spring.api.utility.Global;
import com.nugroho.spring.api.utility.Response;
import com.nugroho.spring.api.utility.ResponseSuccess;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@RestController
@RequestMapping(Routes.API_V1)
public class IndexController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.topicExchange}")
    private String topicExchange;

    private ResponseSuccess response = new ResponseSuccess();

    private Gson gson = new Gson();

    @GetMapping("/test")
    @PreAuthorize("hasPermission(returnObject, '" + Middleware.CHECK_AUTH + "')")
    public ResponseEntity<Response> testAPI() {
        response.setMessage("This is service author, book");
        return Global.resSuccess(response);
    }

    @GetMapping("/test-queue")
    public ResponseEntity<Response> testQueue() throws Exception {
        var data = new BookReturnJobModel();
        data.setId("12");
        data.setTo("13");
        data.setMessage("Ayo kembalikan...");
        var messageJson = gson.toJson(data);
        var bookJob = new BookReturnJob(rabbitTemplate, topicExchange, "bank.return", messageJson);
        bookJob.sendJob();
        return Global.resSuccess(response);
    }

}
