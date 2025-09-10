package com.url_shortener.controller;


import com.url_shortener.dto.LongUrlDto;
import com.url_shortener.dto.ShortenUrlResponseDto;
import com.url_shortener.service.UrlMappingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class UrlShortenerController {
    private final UrlMappingService service;

    public UrlShortenerController(UrlMappingService service) {
        this.service = service;
    }

    @PostMapping("/shortenUrl")
    public ResponseEntity<ShortenUrlResponseDto> createShortUrl(@RequestBody LongUrlDto long_url_dto){
        String longUrl=long_url_dto.getLong_url();
        String shortCode= service.createShortUrl(longUrl);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath() // Gets the base path (e.g., http://localhost:8080)
                .path("/{shortCode}")       // Appends our path variable structure
                .buildAndExpand(shortCode)  // Fills in the shortCode
                .toUri();


        ShortenUrlResponseDto shortenUrlResponseDto=new ShortenUrlResponseDto();
        shortenUrlResponseDto.setShort_url(location.toString());
        return ResponseEntity.created(location).body(shortenUrlResponseDto);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> gotoShortUrl(@PathVariable String shortCode){
        String longUrl=service.getLongUrl(shortCode);
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(URI.create(longUrl))
                .build();
    }
}
