package com.au.ymor.rest.handler;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Ayhan.Ugurlu on 04/10/2018
 */
@AllArgsConstructor
@Getter
public class ErrResponse {
    private long traceID;

    private String message;
}