package com.au.ymor.service.exception;


import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Ayhan.Ugurlu on 04/10/2018
 */
public abstract class TemplateException extends Exception {

    TemplateException(String errorMessage){
        super(errorMessage);
    }

    protected List<String> errors = new ArrayList<>();

    public abstract String getErrorCode();

    public abstract HttpStatus getHttpStatus();

    public List<String> getErrors() {
        return errors;
    }

}