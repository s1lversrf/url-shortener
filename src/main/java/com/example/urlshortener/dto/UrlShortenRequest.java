package com.example.urlshortener.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UrlShortenRequest(
        @Pattern(regexp = "^(https?|ftp)://[^\\s/$.?#].\\S*$", message = "Not a valid URL")
        @NotNull(message = "Field 'url' is required")
        String url
) {}
