package com.jignesh.linksy.dto;

import com.jignesh.linksy.model.UrlMapping;

import java.time.LocalDateTime;

public class UrlResponse {

    private String shortCode;
    private String originalUrl;
    private LocalDateTime createdAt;
    private Long clickCount;
    private LocalDateTime expiryDate;

    public UrlResponse() {
    }

    public UrlResponse(String shortCode, String originalUrl, LocalDateTime createdAt, Long clickCount, LocalDateTime expiryDate) {
        this.shortCode = shortCode;
        this.originalUrl = originalUrl;
        this.createdAt = createdAt;
        this.clickCount = clickCount;
        this.expiryDate = expiryDate;
    }

    public static UrlResponse fromEntity(UrlMapping mapping) {
        return new UrlResponse(
                mapping.getShortCode(),
                mapping.getOriginalUrl(),
                mapping.getCreatedAt(),
                mapping.getClickCount(),
                mapping.getExpiryDate()
        );
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getClickCount() {
        return clickCount;
    }

    public void setClickCount(Long clickCount) {
        this.clickCount = clickCount;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }
}
