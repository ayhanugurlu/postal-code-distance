package com.au.ymor.service;

import com.au.ymor.db.model.PostalCode;
import com.au.ymor.db.repository.PostalCodeRepository;
import com.au.ymor.service.dto.input.GetDistanceInput;
import com.au.ymor.service.dto.input.PostalCodeLocationUpdateInput;
import com.au.ymor.service.dto.output.GetDistanceOutput;
import com.au.ymor.service.dto.PostalCodeDTO;
import com.au.ymor.service.dto.output.PostalCodeOutput;
import com.au.ymor.service.exception.PostalCodeNotFoundException;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Ayhan.Ugurlu on 04/10/2018
 */
@Service
@Slf4j
public class PostalCodeServiceImpl implements PostalCodeService{

    @Autowired
    PostalCodeRepository postalCodeRepository;

    @Autowired
    DistanceService distanceService;

    @Autowired
    private Tracer tracer;

    @Qualifier("postalCodeServiceMapper")
    @Autowired
    private MapperFacade mapperFacade;


    @Override
    public void addPostalCode(PostalCodeDTO postalCodeDTO) {
        log.debug("addPostalCode method start");
        PostalCode postalCode =  mapperFacade.map(postalCodeDTO,PostalCode.class);
        log.debug("addPostalCode method finish");
        postalCodeRepository.save(postalCode);
    }

    @Override
    public GetDistanceOutput getGetDistanceBetweenPostalCode(GetDistanceInput getDistanceInput) throws PostalCodeNotFoundException {
        log.debug("getGetDistanceBetweenPostalCode method start {}",tracer.getCurrentSpan().getTraceId());
        Optional<PostalCode> postalCode1 =  postalCodeRepository.findByPostalCode(getDistanceInput.getPostCode1());
        PostalCode postalCodeValue1 = postalCode1.orElseThrow(() -> new PostalCodeNotFoundException());
        Optional<PostalCode> postalCode2 =  postalCodeRepository.findByPostalCode(getDistanceInput.getPostCode2());
        PostalCode postalCodeValue2 = postalCode2.orElseThrow(() -> new PostalCodeNotFoundException());

        Double distance = distanceService.calculateDistance(postalCodeValue1.getLatitude(),postalCodeValue1.getLongitude(),postalCodeValue2.getLatitude(),postalCodeValue2.getLongitude());
        PostalCodeOutput postalCodeOutput1 =  mapperFacade.map(postalCodeValue1,PostalCodeOutput.class);
        PostalCodeOutput postalCodeOutput2 =  mapperFacade.map(postalCodeValue2,PostalCodeOutput.class);
        GetDistanceOutput getDistanceOutput = new GetDistanceOutput();
        getDistanceOutput.setPostalCodeOutput1(postalCodeOutput1);
        getDistanceOutput.setPostalCodeOutput2(postalCodeOutput2);
        getDistanceOutput.setDistance(distance);
        log.debug("getGetDistanceBetweenPostalCode method finish {}",tracer.getCurrentSpan().getTraceId());
        return getDistanceOutput;
    }


    @Override
    public void updatePostalCode(PostalCodeLocationUpdateInput postalCodeLocationUpdateInput) throws PostalCodeNotFoundException {
        log.debug("updatePostalCode method start {}",tracer.getCurrentSpan().getTraceId());
        Optional<PostalCode> postalCode =  postalCodeRepository.findByPostalCode(postalCodeLocationUpdateInput.getPostalCode());
        PostalCode postalCodeValue = postalCode.orElseThrow(() -> new PostalCodeNotFoundException());
        postalCodeValue.setLatitude(postalCodeLocationUpdateInput.getLatitude());
        postalCodeValue.setLongitude(postalCodeLocationUpdateInput.getLongitude());
        log.debug("updatePostalCode method finish {}",tracer.getCurrentSpan().getTraceId());
        postalCodeRepository.save(postalCodeValue);

    }


    @Override
    public PostalCodeDTO parseDataToPostalCode(String line) {
        log.debug("parseDataToPostalCode method start ");
        PostalCodeDTO postalCodeDTO = new PostalCodeDTO();
        try {

            String[] datas = line.split(",");
            postalCodeDTO.setId(Long.parseLong(datas[0]));
            postalCodeDTO.setPostalCode(datas[1]);
            postalCodeDTO.setLatitude(Double.parseDouble(datas[2]));
            postalCodeDTO.setLongitude(Double.parseDouble(datas[3]));

        }catch (Exception e){
            postalCodeDTO = null;
        }
        log.debug("parseDataToPostalCode method finish ");
        return postalCodeDTO;
    }



}
