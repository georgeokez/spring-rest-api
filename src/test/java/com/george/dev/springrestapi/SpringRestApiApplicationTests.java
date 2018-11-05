package com.george.dev.springrestapi;

import com.george.dev.springrestapi.services.GenerateTokenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringRestApiApplicationTests {

    private final Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private GenerateTokenService generateTokenService;


    @Test
    public void contextLoads() {

        String actualToken =  "Basic c3VwZXJ1c2VyOnBhc3MxMjM=";

        String resourceId = "superuser";
        String verifierkey = "pass123";

        String generatedAccessToken = generateTokenService.generateToken(resourceId, verifierkey);

        logger.info(String.format("Generated token is: %s", generatedAccessToken));

        assertThat(generatedAccessToken, is(equalTo(actualToken)));

        String decodedCredentials = generateTokenService.decodeAuthDetails(generatedAccessToken);
        System.out.println(decodedCredentials);
        logger.info(String.format("Decoded token is: %s", decodedCredentials));
    }

}
