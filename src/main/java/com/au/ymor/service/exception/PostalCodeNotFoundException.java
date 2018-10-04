package com.au.ymor.service.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by Ayhan.Ugurlu on 04/10/2018
 */
public class PostalCodeNotFoundException extends TemplateException  {

    private static final  String ERR_CODE = "001";

    public PostalCodeNotFoundException() {
        super(ERR_CODE);
    }

    @Override
    public String getErrorCode() {
        return ERR_CODE;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return  HttpStatus.NOT_ACCEPTABLE;
    }
}
