package com.au.ymor.listener;

import com.au.ymor.service.PostalCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Created by Ayhan.Ugurlu on 04/10/2018
 */
@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    PostalCodeService postalCodeService;
    @Value("${import.file.name}")
    private String fileName;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

        Resource resource = new ClassPathResource(fileName);
        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(resource.getURI()))) {

            stream.map(s -> {
                return postalCodeService.parseDataToPostalCode(s);
            }).filter(Objects::nonNull).parallel().forEach(postalCodeDTO -> postalCodeService.addPostalCode(postalCodeDTO));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}