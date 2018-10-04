package com.au.ymor.service.exception;


import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Template exeption
 * <p>
 * Created by Ayhan.Ugurlu on 04/10/2018
 */
public abstract class TemplateException extends Exception {

    protected List<String> errors = new ArrayList<>();

    TemplateException(String errorMessage) {
        super(errorMessage);
    }

    public abstract String getErrorCode();

    public abstract HttpStatus getHttpStatus();

    public List<String> getErrors() {
        return errors;
    }

}