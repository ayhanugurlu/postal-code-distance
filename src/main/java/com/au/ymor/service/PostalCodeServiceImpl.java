package com.au.ymor.service;

import com.au.ymor.db.model.PostalCode;
import com.au.ymor.db.repository.PostalCodeRepository;
import com.au.ymor.service.dto.GetDistanceInput;
import com.au.ymor.service.dto.GetDistanceOutput;
import com.au.ymor.service.dto.PostalCodeDTO;
import com.au.ymor.service.dto.PostalCodeOutput;
import com.au.ymor.service.exception.PostalCodeNotFoundException;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

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

    @Qualifier("postalCodeServiceMapper")
    @Autowired
    private MapperFacade mapperFacade;


    @Override
    public void addPostalCode(PostalCodeDTO postalCodeDTO) {
        log.debug("postal code:" + postalCodeDTO.getPostalCode()+" postal code lat:" + postalCodeDTO.getLatitude()+" postal code lon:"+postalCodeDTO.getLongitude());
        PostalCode postalCode =  mapperFacade.map(postalCodeDTO,PostalCode.class);
        postalCodeRepository.save(postalCode);
    }

    @Override
    public GetDistanceOutput getGetDistanceBetweenPostalCode(GetDistanceInput getDistanceInput) throws PostalCodeNotFoundException {

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
        return getDistanceOutput;
    }


    @Override
    public void updatePostalCode(PostalCodeDTO postalCodeDTO) throws PostalCodeNotFoundException {

        Optional<PostalCode> postalCode =  postalCodeRepository.findByPostalCode(postalCodeDTO.getPostalCode());
        PostalCode postalCodeValue = postalCode.orElseThrow(() -> new PostalCodeNotFoundException());
        postalCodeValue.setLatitude(postalCodeDTO.getLatitude());
        postalCodeValue.setLongitude(postalCodeDTO.getLongitude());
        postalCodeRepository.save(postalCodeValue);

    }


    @Override
    public PostalCodeDTO parseDataToPostalCode(String line) {
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
        return postalCodeDTO;
    }



}
