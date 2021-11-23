package com.nugroho.spring.api.utility;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonNaming(SnakeCaseStrategy.class)
public class ResponseSuccess implements Response {
    private String api = "v1";
    private int code = 0;
    private String message;
    private Object data;
}
