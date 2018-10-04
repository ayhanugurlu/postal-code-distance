package com.au.ymor.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Ayhan.Ugurlu on 04/10/2018
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetDistanceOutput {

    private PostalCodeOutput postalCodeOutput1;

    private PostalCodeOutput postalCodeOutput2;

    private double distance;
}
