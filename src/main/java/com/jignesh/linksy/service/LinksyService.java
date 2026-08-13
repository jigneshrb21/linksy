package com.jignesh.linksy.service;

import com.jignesh.linksy.exception.ShortCodeNotFoundException;
import com.jignesh.linksy.model.UrlMapping;
import com.jignesh.linksy.repository.UrlMappingRepository;
import com.jignesh.linksy.util.Base62Encoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LinksyService {

    private final UrlMappingRepository urlMappingRepository;

    public LinksyService(UrlMappingRepository urlMappingRepository) {
        this.urlMappingRepository = urlMappingRepository;
    }

    public String shortenUrl(String originalUrl) {
        UrlMapping mapping = new UrlMapping();
        mapping.setOriginalUrl(originalUrl);
        mapping.setCreatedAt(LocalDateTime.now());
        mapping.setClickCount(0L);

        UrlMapping saved = urlMappingRepository.save(mapping);

        saved.setShortCode(Base62Encoder.encode(saved.getId()));
        urlMappingRepository.save(saved);

        return saved.getShortCode();
    }

    public String getOriginalUrl(String shortCode) {
        UrlMapping mapping = urlMappingRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new ShortCodeNotFoundException(shortCode));

        mapping.setClickCount(mapping.getClickCount() + 1);
        urlMappingRepository.save(mapping);

        return mapping.getOriginalUrl();
    }
}
