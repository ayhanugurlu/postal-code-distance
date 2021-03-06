package com.au.ymor.rest.handler;

/**
 * Created by Ayhan.Ugurlu on 04/10/2018
 */


import com.au.ymor.service.exception.PostalCodeNotFoundException;
import com.au.ymor.service.exception.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ayhanugurlu on 5/26/18.
 */
@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @Value("${error.code.header.key}")
    private String errorCodeHeaderKey;

    @Autowired
    private Tracer tracer;

    @ExceptionHandler({PostalCodeNotFoundException.class})
    public ResponseEntity<ErrResponse> handleTemplateException(HttpServletRequest request, TemplateException e) {

        String[] eCodes = {e.getErrorCode()};
        ErrResponse response = new ErrResponse(tracer.getCurrentSpan().getTraceId(), e.getMessage());
        return ResponseEntity
                .status(e.getHttpStatus())
                .header(errorCodeHeaderKey, eCodes)
                .body(response);
    }


}