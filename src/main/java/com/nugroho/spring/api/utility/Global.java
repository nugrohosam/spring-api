package com.nugroho.spring.api.utility;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nugroho.spring.api.applications.requests.v1.Params;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

import io.sentry.Sentry;

public class Global {

    public static String projectDir() {
        return System.getProperty("user.dir");
    }

    public static void report(String message) {
        Sentry.captureMessage(message);
    }

    public static void report(Throwable throwable) {
        Sentry.captureException(throwable);
    }

    public static ResponseEntity<Response> resSuccess(Response res) {
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    public static ResponseEntity<Response> resFail(Response res, HttpStatus status) {
        return new ResponseEntity<>(res, status);
    }

    public static ResponseEntity<String> resSuccess(String res) {
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    public static String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    public static Object generateCacheKey(Object target, Method method, Object... params) {

        var tagetClass = target.getClass().getSimpleName();
        var methodGet = method.getName();
        var key = tagetClass + "_" + methodGet;
        var isFound = false;

        // Define key search from params
        for (Object param : params) {
            if (Params.isInstanceOfMe(param)) {
                isFound = true;
                key += "_" + Params.generateOfKey((Params) param);
            }
        }

        if (!isFound) {
            key += "_" + StringUtils.arrayToDelimitedString(params, "_");
        }

        return key;
    }

    public static String dateFormat(String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());
    }

}
