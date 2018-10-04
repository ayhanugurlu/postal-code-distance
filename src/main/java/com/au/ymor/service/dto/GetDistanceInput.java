package com.au.ymor.service.dto;

import lombok.*;

/**
 * Created by Ayhan.Ugurlu on 04/10/2018
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetDistanceInput {

    private String postCode1;
    private String postCode2;
}
