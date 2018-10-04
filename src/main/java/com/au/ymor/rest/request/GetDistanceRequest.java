package com.au.ymor.rest.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Ayhan.Ugurlu on 04/10/2018
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetDistanceRequest {

    private String postCode1;
    private String postCode2;
}
