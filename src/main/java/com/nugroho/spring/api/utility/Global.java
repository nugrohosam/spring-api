package com.nugroho.spring.api.utility;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.sentry.Sentry;

public class Global {

    public static void report(String message) {
        Sentry.captureMessage(message);
    }

    public static void report(Throwable throwable) {
        Sentry.captureException(throwable);
    }

    public static ResponseEntity<Response> resSuccess(Response res) {
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    public static ResponseEntity<String> resSuccess(String res) {
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    public static List<String> getFieldNames(Field[] fields) {
        List<String> fieldNames = new ArrayList<>();
        for (Field field : fields)
          fieldNames.add(field.getName());
        return fieldNames;
    }
}
