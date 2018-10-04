package com.au.ymor.service;

import com.au.ymor.db.model.PostalCode;
import com.au.ymor.db.model.PostalCodeHistory;
import com.au.ymor.db.repository.PostalCodeHistoryRepository;
import com.au.ymor.db.repository.PostalCodeRepository;
import com.au.ymor.service.dto.PostalCodeDTO;
import com.au.ymor.service.dto.input.GetDistanceInput;
import com.au.ymor.service.dto.input.PostalCodeLocationUpdateInput;
import com.au.ymor.service.dto.output.GetDistanceOutput;
import com.au.ymor.service.exception.PostalCodeNotFoundException;
import ma.glasnost.orika.MapperFacade;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.Mockito.when;

/**
 * Created by Ayhan.Ugurlu on 04/10/2018
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PostalCodeServiceTest {

    @Autowired
    PostalCodeService postalCodeService;
    @Autowired
    PostalCodeRepository postalCodeRepository;
    @Autowired
    PostalCodeHistoryRepository postalCodeHistoryRepository;
    @Qualifier("postalCodeServiceMapper")
    @Autowired
    private MapperFacade mapperFacade;
    @MockBean
    private Tracer tracer;

    @MockBean
    private Span span;


    @Before
    public void setUp() throws Exception {
        when(span.getTraceId()).thenReturn(1l);
        when(tracer.getCurrentSpan()).thenReturn(span);
        PostalCode postalCodeA = PostalCode.builder().postalCode("b").latitude(2).longitude(2).id(4).build();
        postalCodeRepository.save(postalCodeA);
        PostalCode postalCodeZ = PostalCode.builder().postalCode("z").latitude(2).longitude(2).id(4).build();
        postalCodeRepository.save(postalCodeZ);
    }

    @Test
    public void accountServiceTest() {
        PostalCodeDTO postalCodeDTO = PostalCodeDTO.builder().postalCode("a").latitude(1).longitude(2).id(3).build();
        postalCodeService.addPostalCode(postalCodeDTO);
        PostalCode postalCode = postalCodeRepository.findOne(3l);
        Assert.assertEquals(postalCodeDTO.getId(), postalCode.getId());
        Assert.assertEquals(postalCodeDTO.getPostalCode(), postalCode.getPostalCode());
        Assert.assertEquals(postalCodeDTO.getLatitude(), postalCode.getLatitude(), 0.1);
        Assert.assertEquals(postalCodeDTO.getLongitude(), postalCode.getLongitude(), 0.1);

    }

    @Test
    public void parseDataToPostalCodeTest() {
        PostalCodeDTO postalCodeDTO = postalCodeService.parseDataToPostalCode("14,AB30,56.84678,-2.47712");
        Assert.assertEquals(postalCodeDTO.getId(), 14);
        Assert.assertEquals(56.84678, postalCodeDTO.getLatitude(), 0.1);
        Assert.assertEquals(-2.47712, postalCodeDTO.getLongitude(), 0.1);
        Assert.assertEquals(postalCodeDTO.getPostalCode(), "AB30");
    }

    @Test
    public void getGetDistanceBetweenPostalCodeTest() {

        GetDistanceInput getDistanceInput = GetDistanceInput.builder().postCode1("c").postCode2("b").build();

        try {
            GetDistanceOutput postalCodeDTO = postalCodeService.getGetDistanceBetweenPostalCode(getDistanceInput);
        } catch (PostalCodeNotFoundException e) {
            e.printStackTrace();
        }
        getDistanceInput = GetDistanceInput.builder().postCode1("AB22").postCode2("AB23").build();
        try {
            GetDistanceOutput postalCodeDTO = postalCodeService.getGetDistanceBetweenPostalCode(getDistanceInput);
            Assert.assertEquals(postalCodeDTO.getDistance(), 3.3778562717664498, 0.1);
        } catch (PostalCodeNotFoundException e) {
            e.printStackTrace();
        }

        getDistanceInput = GetDistanceInput.builder().postCode1("AB22").postCode2("AB23").build();
        try {
            GetDistanceOutput postalCodeDTO = postalCodeService.getGetDistanceBetweenPostalCode(getDistanceInput);
            Optional<PostalCodeHistory> postalCodeHistory = postalCodeHistoryRepository.findByPostalCode1AndPostalCode2("AB22", "AB23");
            Assert.assertEquals(postalCodeHistory.isPresent(), true);
        } catch (PostalCodeNotFoundException e) {
            e.printStackTrace();
        }


    }


    @Test
    public void updatePostalCodeTest() {
        PostalCodeLocationUpdateInput postalCodeZ = PostalCodeLocationUpdateInput.builder().postalCode("z").latitude(4).longitude(2).build();
        try {
            postalCodeService.updatePostalCode(postalCodeZ);
            PostalCode postalCode = postalCodeRepository.findOne(4l);
            Assert.assertEquals(postalCode.getLatitude(), postalCodeZ.getLatitude(), 0.1);
        } catch (PostalCodeNotFoundException e) {
            e.printStackTrace();
        }
        postalCodeZ = PostalCodeLocationUpdateInput.builder().postalCode("k").latitude(4).longitude(2).build();
        try {
            postalCodeService.updatePostalCode(postalCodeZ);
        } catch (PostalCodeNotFoundException e) {
            e.printStackTrace();
        }

    }

}
