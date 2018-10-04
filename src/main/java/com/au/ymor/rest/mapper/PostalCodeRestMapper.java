package com.au.ymor.rest.mapper;

import com.au.ymor.rest.request.GetDistanceRequest;
import com.au.ymor.rest.request.PostalCodeLocationUpdateRequest;
import com.au.ymor.rest.response.GetDistanceResponse;
import com.au.ymor.rest.response.PostalCodeResponse;
import com.au.ymor.service.dto.input.GetDistanceInput;
import com.au.ymor.service.dto.input.PostalCodeLocationUpdateInput;
import com.au.ymor.service.dto.output.GetDistanceOutput;
import com.au.ymor.service.dto.output.PostalCodeOutput;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

/**
 * Created by Ayhan.Ugurlu on 04/10/2018
 */
@Component
public class PostalCodeRestMapper extends ConfigurableMapper {
    protected void configure(MapperFactory factory) {
        factory.classMap(GetDistanceRequest.class, GetDistanceInput.class)
                .byDefault()
                .register();


        factory.classMap(GetDistanceOutput.class, GetDistanceResponse.class)
                .byDefault()
                .register();

        factory.classMap(PostalCodeOutput.class, PostalCodeResponse.class)
                .byDefault()
                .register();

        factory.classMap(PostalCodeLocationUpdateRequest.class, PostalCodeLocationUpdateInput.class)
                .byDefault()
                .register();
    }
}
