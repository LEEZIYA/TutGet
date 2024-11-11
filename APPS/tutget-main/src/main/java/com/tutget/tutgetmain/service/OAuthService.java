package com.tutget.tutgetmain.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;

@Service
public class OAuthService {

    private final RestTemplate restTemplate;

    public OAuthService() {
        restTemplate = new RestTemplate();
    }

    @Value("${oauth.issuer-uri}")
    private String jwksUrl;

    public PublicKey getPublicKey() {
        try {
            String jwksResponse = restTemplate.getForObject(jwksUrl, String.class);
            JsonNode jsonNode = new ObjectMapper().readTree(jwksResponse);
            JsonNode keyNode = jsonNode.get("keys").get(0);

            // Extract modulus and exponent
            String modulusBase64 = keyNode.get("n").asText();
            String exponentBase64 = keyNode.get("e").asText();

            // Decode base64 to BigIntegers
            BigInteger modulus = new BigInteger(1, Base64.getUrlDecoder().decode(modulusBase64));
            BigInteger exponent = new BigInteger(1, Base64.getUrlDecoder().decode(exponentBase64));

            // Create RSA public key spec
            RSAPublicKeySpec rsaPublicKeySpec = new RSAPublicKeySpec(modulus, exponent);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            return keyFactory.generatePublic(rsaPublicKeySpec);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve public key from Keycloak", e);
        }
    }
}
