package com.au.ymor.service.mapper;

import com.au.ymor.db.model.PostalCode;
import com.au.ymor.service.DistanceService;
import com.au.ymor.service.dto.PostalCodeDTO;
import com.au.ymor.service.dto.PostalCodeOutput;
import com.au.ymor.service.util.Deg2UTM;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Ayhan.Ugurlu on 04/10/2018
 */
@Component
public class PostalCodeServiceMapper extends ConfigurableMapper {

    @Autowired
    DistanceService distanceService;

    protected void configure(MapperFactory factory) {
        factory.classMap(PostalCodeDTO.class, PostalCode.class)
                .byDefault()
                .register();

        factory.classMap(PostalCode.class, PostalCodeOutput.class)
                .customize(new CustomMapper<PostalCode, PostalCodeOutput>() {
                    @Override
                    public void mapAtoB(PostalCode postalCode, PostalCodeOutput postalCodeOutput, MappingContext context) {
                        postalCodeOutput.setDegree(distanceService.getFormattedLocationInDegree(postalCode.getLatitude(), postalCode.getLongitude()));
                        super.mapAtoB(postalCode, postalCodeOutput, context);
                    }
                })
                .byDefault()
                .register();


    }
}
