package com.nugroho.spring.api.utility;

import lombok.Data;

@Data
public class ResponseSuccess implements Response {
    private String api = "v1";
    private int code = 0;
    private String message;
    private Object data;
}
