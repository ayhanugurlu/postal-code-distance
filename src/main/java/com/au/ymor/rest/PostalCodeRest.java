package com.au.ymor.rest;

import com.au.ymor.rest.request.GetDistanceRequest;
import com.au.ymor.rest.response.GetDistanceResponse;
import com.au.ymor.rest.response.PostalCodeResponse;
import com.au.ymor.service.PostalCodeService;
import com.au.ymor.service.dto.GetDistanceInput;
import com.au.ymor.service.dto.GetDistanceOutput;
import com.au.ymor.service.exception.PostalCodeNotFoundException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Ayhan.Ugurlu on 04/10/2018
 */
@Slf4j
@RestController
@RequestMapping("api/v1")
public class PostalCodeRest {


    @Autowired
    @Qualifier("postalCodeRestMapper")
    private MapperFacade mapperFacade;

    @Autowired
    PostalCodeService postalCodeService;

    @Autowired
    private Tracer tracer;


    @ApiOperation(value = "Getting two postalcode between distance",
            notes = "Getting two postalcode between distance<br/>")
    @PostMapping("getPostalCodeDistance")
    public @ResponseBody
    GetDistanceResponse getPostalCodeDistance(@ApiParam(value = "Postal codes") @RequestBody GetDistanceRequest getDistanceRequest) throws PostalCodeNotFoundException {
        log.debug("getPostalCodeDistance method start" + tracer.getCurrentSpan().getTraceId());
        GetDistanceInput input = mapperFacade.map(getDistanceRequest, GetDistanceInput.class);
        GetDistanceOutput output = postalCodeService.getGetDistanceBetweenPostalCode(input);
        GetDistanceResponse response = mapperFacade.map(output, GetDistanceResponse.class);
        response.setPostalCodeOutput1(mapperFacade.map(output.getPostalCodeOutput1(), PostalCodeResponse.class));
        response.setPostalCodeOutput2(mapperFacade.map(output.getPostalCodeOutput2(), PostalCodeResponse.class));
        log.debug("getPostalCodeDistance method finish"+ tracer.getCurrentSpan().getTraceId());
        return response;
    }

}
