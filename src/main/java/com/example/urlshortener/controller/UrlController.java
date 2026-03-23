package com.example.urlshortener.controller;

import com.example.urlshortener.dto.Url;
import com.example.urlshortener.dto.UrlShortenRequest;
import com.example.urlshortener.dto.UrlShortenResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.urlshortener.service.UrlService;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/url-shortener")
public class UrlController {
    @Value("${application.base-url}")
    String applicationBaseUrl;

    private final UrlService urlService;

    @PostMapping
    public ResponseEntity<UrlShortenResponse> shortenUrl(@Valid @RequestBody UrlShortenRequest request) {
        Url shortUrl = urlService.shortenUrl(request.url());

        UrlShortenResponse urlShortenResponse = new UrlShortenResponse(
                applicationBaseUrl + shortUrl.getShortCode());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(urlShortenResponse);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {
        Url url = urlService.getOriginalUrl(shortCode);
        if (url == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url.getUrl()));

        return ResponseEntity
                .status(HttpStatus.PERMANENT_REDIRECT)
                .headers(headers)
                .build();
    }
}
