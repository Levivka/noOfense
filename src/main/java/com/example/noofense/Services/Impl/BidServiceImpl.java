package com.example.noofense.Services.Impl;

import com.example.noofense.Models.Bid;
import com.example.noofense.Services.BidService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class BidServiceImpl implements BidService {
    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:8083/bids";

    public BidServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Bid> listBids() {
        String endpoint = baseUrl + "/list";
        ResponseEntity<List<Bid>> response = restTemplate.exchange(
                endpoint,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Bid>>() {}
        );
        if (response.getBody() == null) {
            log.info("No bids found");
            return null;
        }
        return response.getBody();
    }
}

