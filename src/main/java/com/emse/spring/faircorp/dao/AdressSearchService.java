package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.dto.ApiGouvAdressDto;
import com.emse.spring.faircorp.dto.ApiGouvFeatureDto;
import com.emse.spring.faircorp.dto.ApiGouvResponseDto;
import com.emse.spring.faircorp.model.Room;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdressSearchService {
    private final RestTemplate restTemplate;

    public AdressSearchService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.rootUri("https://adresse.data.gouv.fr").build();
    }

    public List<ApiGouvAdressDto> findAdress(List<String> keys) {
        String params = String.join("+", keys);
        String uri = UriComponentsBuilder.fromUriString("https://adresse.data.gouv.fr/search").queryParam("q", params).queryParam("limit", 15).build().toUriString();
        return restTemplate
                .getForObject(uri, ApiGouvResponseDto.class)
                .getFeatures()
                .stream().map(ApiGouvFeatureDto::getProperties)
                .collect(Collectors.toList());

    }
}
