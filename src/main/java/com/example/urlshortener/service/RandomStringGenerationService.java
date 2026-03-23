package com.example.urlshortener.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class RandomStringGenerationService {
    @Value("${short-url-length}")
    private int shortUrlLength;

    public String generateRandomBase62String(){
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(shortUrlLength);
        for (int i = 0; i < shortUrlLength; i++) {
            sb.append(chars.charAt(random.nextInt(62)));
        }
        return sb.toString();
    }
}
