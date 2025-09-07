package com.url_shortener.service;


import com.url_shortener.Model.UrlMapping;

public interface UrlMappingService {

    String createShortUrl(String long_url);
    String getLongUrl(String shortCode);
}
