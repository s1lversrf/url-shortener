package com.example.urlshortener.repository;

import com.example.urlshortener.dto.Url;
import org.springframework.data.repository.CrudRepository;

public interface UrlRepository extends CrudRepository<Url, String> {}
