package com.au.ymor.rest.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Ayhan.Ugurlu on 04/10/2018
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostalCodeResponse {

    private String postalCode;

    private double latitude;

    private double longitude;

    private String degree;
}
