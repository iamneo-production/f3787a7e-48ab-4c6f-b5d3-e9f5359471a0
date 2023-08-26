package com.tech_tribe.goalsservice.Security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
@Slf4j
public class KeyUtils {

    @Value("${public-key-url}")
    private String publicKeyUrl;

    protected RSAPublicKey getAccessTokenPublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String base64PublicKey = getPublicKeyFromAuthServer();
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] publicKeyBytes = Base64.getDecoder().decode(base64PublicKey);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(publicKeyBytes);
        PublicKey publicKey = keyFactory.generatePublic(spec);

        return (RSAPublicKey) publicKey;
    }

    private String getPublicKeyFromAuthServer() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(publicKeyUrl, String.class);
            return response.getBody();
        } catch (HttpStatusCodeException ex) {
            throw new RestClientException("Error retrieving public key from auth server",ex);
        }
    }
}

