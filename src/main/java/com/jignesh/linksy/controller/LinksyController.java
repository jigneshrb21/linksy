package com.jignesh.linksy.controller;

import com.jignesh.linksy.dto.ShortenRequest;
import com.jignesh.linksy.dto.UrlResponse;
import com.jignesh.linksy.service.LinksyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
public class LinksyController {

    @Value("${app.base-url}")
    private String baseUrl;

    private final LinksyService linksyService;

    public LinksyController(LinksyService linksyService) {
        this.linksyService = linksyService;
    }

    @PostMapping("/api/shorten")
    public ResponseEntity<Map<String, String>> shortenUrl(@Valid @RequestBody ShortenRequest request) {
        String shortCode = linksyService.shortenUrl(request.getOriginalUrl(), request.getCustomAlias(), request.getExpiryDate());
        String shortUrl = baseUrl + "/" + shortCode;
        return ResponseEntity.ok(Map.of("shortUrl", shortUrl));
    }

    @GetMapping("/api/urls")
    public ResponseEntity<List<UrlResponse>> getAllUrls() {
        return ResponseEntity.ok(linksyService.getAllUrls());
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {
        String originalUrl = linksyService.getOriginalUrl(shortCode);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(originalUrl))
                .build();
    }
}

