package com.au.ymor.rest.response;

import lombok.*;

/**
 * Created by Ayhan.Ugurlu on 04/10/2018
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetDistanceResponse {

    private PostalCodeResponse postalCodeOutput1;

    private PostalCodeResponse postalCodeOutput2;

    private double distance;
}
