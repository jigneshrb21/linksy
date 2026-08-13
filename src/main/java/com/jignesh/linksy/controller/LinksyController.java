package com.jignesh.linksy.controller;

import com.jignesh.linksy.dto.ShortenRequest;
import com.jignesh.linksy.service.LinksyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
public class LinksyController {

    private final LinksyService linksyService;

    public LinksyController(LinksyService linksyService) {
        this.linksyService = linksyService;
    }

    @PostMapping("/api/shorten")
    public ResponseEntity<Map<String, String>> shortenUrl(@Valid @RequestBody ShortenRequest request) {
        String shortCode = linksyService.shortenUrl(request.getOriginalUrl());
        String shortUrl = "http://localhost:8080/" + shortCode;
        return ResponseEntity.ok(Map.of("shortUrl", shortUrl));
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {
        String originalUrl = linksyService.getOriginalUrl(shortCode);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(originalUrl))
                .build();
    }
}
