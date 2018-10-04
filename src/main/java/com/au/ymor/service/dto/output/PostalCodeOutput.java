package com.au.ymor.service.dto.output;

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
public class PostalCodeOutput {

    private String postalCode;

    private double latitude;

    private double longitude;

    private String degree;
}
