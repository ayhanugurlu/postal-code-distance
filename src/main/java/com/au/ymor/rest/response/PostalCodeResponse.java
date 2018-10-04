package com.au.ymor.rest.response;

import lombok.*;

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
