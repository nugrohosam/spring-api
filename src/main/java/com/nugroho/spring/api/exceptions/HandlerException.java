package com.nugroho.spring.api.exceptions;

import com.nugroho.spring.api.utility.Global;
import com.nugroho.spring.api.utility.Response;
import com.nugroho.spring.api.utility.ResponseFail;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class HandlerException extends ResponseEntityExceptionHandler {

    private ResponseFail response = new ResponseFail();

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Response> handle(AccessDeniedException ex, WebRequest request) {
        response.setMessage("Access denied");
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Response> handle(DataIntegrityViolationException ex, WebRequest request) {
        response.setMessage("Internal service error");
        Global.report(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Response> handle(RuntimeException ex, WebRequest request) {
        response.setMessage("Internal service error");
        Global.report(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handle(Exception ex, WebRequest request) {
        response.setMessage("Internal service error");
        Global.report(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
