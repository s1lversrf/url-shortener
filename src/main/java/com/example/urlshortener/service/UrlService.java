package com.example.urlshortener.service;

import com.example.urlshortener.dto.Url;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.urlshortener.repository.UrlRepository;

@Service
@RequiredArgsConstructor
public class UrlService {
    private final UrlRepository urlRepository;

    private final RandomStringGenerationService randomStringGenerationService;

    public Url shortenUrl(String url){
        String shortCode = randomStringGenerationService.generateRandomBase62String();
        if (urlRepository.existsById(shortCode)){
            shortenUrl(url);
        }
        Url shortUrl = new Url(shortCode, url);
        urlRepository.save(shortUrl);
        return shortUrl;
    }

    public Url getOriginalUrl(String shortCode) {
        return urlRepository.findById(shortCode).orElse(null);
    }
}
