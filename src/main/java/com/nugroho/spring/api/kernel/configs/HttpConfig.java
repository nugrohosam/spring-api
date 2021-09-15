package com.nugroho.spring.api.kernel.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import lombok.RequiredArgsConstructor;

import java.io.IOException;

import com.nugroho.spring.api.applications.requests.HeadersPayload;

@Configuration
@RequiredArgsConstructor
public class HttpConfig implements ClientHttpRequestInterceptor {

    private HeadersPayload headersPayload;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {

        headersPayload.setAuthorization(request.getHeaders().get(HeadersPayload.KEY_AUTHORIZATION).toString());
        headersPayload.setPlatform(request.getHeaders().get(HeadersPayload.KEY_PLATFORM).toString());
        headersPayload.setUtc(request.getHeaders().get(HeadersPayload.KEY_TIME_UTC).toString());
        headersPayload.setRole(request.getHeaders().get(HeadersPayload.KEY_ROLE).toString());

        return execution.execute(request, body);
    }

}