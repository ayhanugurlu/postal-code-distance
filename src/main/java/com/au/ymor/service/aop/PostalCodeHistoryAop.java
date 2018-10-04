package com.au.ymor.service.aop;

import com.au.ymor.db.model.PostalCodeHistory;
import com.au.ymor.db.repository.PostalCodeHistoryRepository;
import com.au.ymor.service.dto.input.GetDistanceInput;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ayhanugurlu on 10/4/18.
 */

@Aspect
@Configuration
public class PostalCodeHistoryAop {

    @Autowired
    PostalCodeHistoryRepository postalCodeHistoryRepository;

    @Before("execution(* com.au.ymor.service.PostalCodeServiceImpl.getGetDistanceBetweenPostalCode(..))")
    public void before(JoinPoint joinPoint) {
        GetDistanceInput getDistanceInput = (GetDistanceInput) joinPoint.getArgs()[0];

        PostalCodeHistory newPostalCodeHistory = PostalCodeHistory.builder().postalCode1(getDistanceInput.
                getPostCode1()).postalCode2(getDistanceInput.
                getPostCode2())
                .build();
        postalCodeHistoryRepository.save(newPostalCodeHistory);


    }
}
