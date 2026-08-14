package com.jignesh.linksy.service;

import com.jignesh.linksy.dto.UrlResponse;
import com.jignesh.linksy.exception.AliasAlreadyExistsException;
import com.jignesh.linksy.exception.LinkExpiredException;
import com.jignesh.linksy.exception.ShortCodeNotFoundException;
import com.jignesh.linksy.model.UrlMapping;
import com.jignesh.linksy.repository.UrlMappingRepository;
import com.jignesh.linksy.util.Base62Encoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LinksyService {

    private final UrlMappingRepository urlMappingRepository;

    public LinksyService(UrlMappingRepository urlMappingRepository) {
        this.urlMappingRepository = urlMappingRepository;
    }

    public String shortenUrl(String originalUrl, String customAlias, LocalDateTime expiryDate) {
        UrlMapping mapping = new UrlMapping();
        mapping.setOriginalUrl(originalUrl);
        mapping.setCreatedAt(LocalDateTime.now());
        mapping.setClickCount(0L);
        mapping.setExpiryDate(expiryDate);

        if (customAlias != null && !customAlias.isBlank()) {
            if (urlMappingRepository.findByShortCode(customAlias).isPresent()) {
                throw new AliasAlreadyExistsException(customAlias);
            }
            mapping.setShortCode(customAlias);
            urlMappingRepository.save(mapping);
            return customAlias;
        }

        UrlMapping saved = urlMappingRepository.save(mapping);
        saved.setShortCode(Base62Encoder.encode(saved.getId()));
        urlMappingRepository.save(saved);

        return saved.getShortCode();
    }

    public String getOriginalUrl(String shortCode) {
        UrlMapping mapping = urlMappingRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new ShortCodeNotFoundException(shortCode));

        if (mapping.getExpiryDate() != null && mapping.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new LinkExpiredException(shortCode);
        }

        mapping.setClickCount(mapping.getClickCount() + 1);
        urlMappingRepository.save(mapping);

        return mapping.getOriginalUrl();
    }

    public List<UrlResponse> getAllUrls() {
        return urlMappingRepository.findAll().stream()
                .map(UrlResponse::fromEntity)
                .toList();
    }
}

