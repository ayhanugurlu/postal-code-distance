package com.au.ymor.rest;


import com.au.ymor.PostalCodeDistanceApplication;
import com.au.ymor.rest.request.GetDistanceRequest;
import com.au.ymor.rest.request.PostalCodeLocationUpdateRequest;
import com.au.ymor.rest.response.GetDistanceResponse;
import org.apache.http.HttpHost;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Ayhan.Ugurlu on 04/10/2018
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PostalCodeDistanceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostalCodeRestTest {


    HttpHeaders headers = new HttpHeaders();

    @LocalServerPort
    private int port;


    @Test
    public void restTest() {

        HttpHost host = new HttpHost("localhost", port, "http");
        RestTemplate  restTemplate = new RestTemplate(
                new HttpComponentsClientHttpRequestFactoryBasicAuth(host));

        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("admin", "admin"));
        GetDistanceRequest getDistanceRequest = GetDistanceRequest.builder().postCode1("OL14").postCode2("EH18").build();
        HttpEntity<GetDistanceRequest> getDistanceRequestHttpEntity = new HttpEntity<>(getDistanceRequest, headers);
        ResponseEntity<GetDistanceResponse> getDistanceResponseEntity = restTemplate.exchange(createURLWithPort("/getPostalCodeDistance"), HttpMethod.POST, getDistanceRequestHttpEntity, GetDistanceResponse.class);
        Assert.assertEquals(getDistanceResponseEntity.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(getDistanceResponseEntity.getBody().getDistance(), 249.2681369479609,0.1);



        PostalCodeLocationUpdateRequest postalCodeLocationUpdateRequest = PostalCodeLocationUpdateRequest.builder().postalCode("AB99").latitude(1).longitude(1).build();
        HttpEntity<PostalCodeLocationUpdateRequest> getDistanceRequestHttpEntityHttpEntity = new HttpEntity<>(postalCodeLocationUpdateRequest, headers);
        restTemplate.exchange(createURLWithPort("/updatePostalCodeLocation"), HttpMethod.POST, getDistanceRequestHttpEntityHttpEntity,String.class);
        Assert.assertEquals(getDistanceResponseEntity.getStatusCode(), HttpStatus.OK);

    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port+"/api/v1" + uri;
    }

}
