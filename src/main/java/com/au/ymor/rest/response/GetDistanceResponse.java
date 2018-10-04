package com.au.ymor.rest.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private String unit;
}
