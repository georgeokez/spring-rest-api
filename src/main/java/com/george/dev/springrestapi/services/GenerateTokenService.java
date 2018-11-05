package com.george.dev.springrestapi.services;

//import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;

/**
 *  Created by George 18/10/2018
 *
 *  Service used for generating tokens
 *
 */

@Service
public class GenerateTokenService {

    public String generateToken(String resourceId, String verifierKey){
        String plainCredentials = String.format("%s:%s",resourceId, verifierKey);
        String base64Credentials = new String(Base64.encodeBase64(plainCredentials.getBytes()));
        String accessToken = String.format("Basic %s",base64Credentials);

        return accessToken;
    }

    // method for decoding authentication details
    public String decodeAuthDetails(String authDetail){
        // Remove Basic from encoded string
        String split[] = authDetail.split(" ");
        String encodedString = split[1];

        String decodedCredentials = new String(Base64.decodeBase64(encodedString.getBytes()));
        return  decodedCredentials;
    }

}
