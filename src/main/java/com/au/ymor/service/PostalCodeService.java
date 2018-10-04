package com.au.ymor.service;

import com.au.ymor.service.dto.PostalCodeDTO;
import com.au.ymor.service.dto.input.GetDistanceInput;
import com.au.ymor.service.dto.input.PostalCodeLocationUpdateInput;
import com.au.ymor.service.dto.output.GetDistanceOutput;
import com.au.ymor.service.exception.PostalCodeNotFoundException;

/**
 * Created by Ayhan.Ugurlu on 04/10/2018
 */
public interface PostalCodeService {

    /**
     * Add postal code information to db
     *
     * @param postalCodeDTO
     */
    void addPostalCode(PostalCodeDTO postalCodeDTO);

    /**
     * Calculate two postal code distance
     * If postalcode not found throw PostalCodeNotFoundException
     *
     * @param getDistanceInput
     * @return
     * @throws PostalCodeNotFoundException
     */
    GetDistanceOutput getGetDistanceBetweenPostalCode(GetDistanceInput getDistanceInput) throws PostalCodeNotFoundException;

    /**
     * postal code csv line parse and return new PostalCodeDTO
     *
     * @param line
     * @return
     */
    PostalCodeDTO parseDataToPostalCode(String line);

    /**
     * Uptade postal code location
     * If postal code not found throw PostalCodeNotFoundException
     *
     * @param postalCodeLocationUpdateInput
     * @throws PostalCodeNotFoundException
     */
    void updatePostalCode(PostalCodeLocationUpdateInput postalCodeLocationUpdateInput) throws PostalCodeNotFoundException;
}
