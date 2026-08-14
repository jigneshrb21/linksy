package com.jignesh.linksy.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class ShortenRequest {

    @NotBlank(message = "originalUrl must not be blank")
    private String originalUrl;

    private String customAlias;

    private LocalDateTime expiryDate;

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getCustomAlias() {
        return customAlias;
    }

    public void setCustomAlias(String customAlias) {
        this.customAlias = customAlias;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }
}
