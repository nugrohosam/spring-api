package com.nugroho.spring.api.utility;

import lombok.Data;

@Data
public class ResponseFail implements Response {
    private String api = "v1";
    private int code;
    private Object message;
    private Object errors;
}
