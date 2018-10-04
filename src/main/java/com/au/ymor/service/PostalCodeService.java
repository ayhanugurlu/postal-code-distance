package com.au.ymor.service;

import com.au.ymor.service.dto.GetDistanceInput;
import com.au.ymor.service.dto.GetDistanceOutput;
import com.au.ymor.service.dto.PostalCodeDTO;
import com.au.ymor.service.exception.PostalCodeNotFoundException;

/**
 * Created by Ayhan.Ugurlu on 04/10/2018
 */
public interface PostalCodeService {

    void addPostalCode(PostalCodeDTO postalCodeDTO);

    GetDistanceOutput getGetDistanceBetweenPostalCode(GetDistanceInput getDistanceInput) throws PostalCodeNotFoundException;

    PostalCodeDTO parseDataToPostalCode(String line);

    void updatePostalCode(PostalCodeDTO postalCodeDTO) throws PostalCodeNotFoundException;
}
