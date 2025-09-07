package com.url_shortener.service.impl;

import com.url_shortener.Model.UrlMapping;
import com.url_shortener.repository.UrlMappingRepository;
import com.url_shortener.service.UrlMappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UrlMappingServiceImpl implements UrlMappingService {
    private static final String BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private final UrlMappingRepository urlMappingRepository;


    @Transactional
    @Override
    public String createShortUrl(String long_url) {

        UrlMapping mapping=new UrlMapping();
        mapping.setLong_url(long_url);
        urlMappingRepository.save(mapping);
        long id=mapping.getId();

        String shortCode=base62Encode(id);
        mapping.setShortCode(shortCode);
        urlMappingRepository.save(mapping);

        return shortCode;

    }

    @Override
    public String getLongUrl(String shortCode) {
        return urlMappingRepository.findByShortCode(shortCode)
                .map(UrlMapping::getLong_url)
                .orElseThrow(()->new RuntimeException("URL not found for short code: " + shortCode));
    }


    private String base62Encode(long n) {
        if (n == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append(BASE62.charAt((int) (n % 62)));
            n /= 62;
        }
        return sb.reverse().toString();
    }
}
