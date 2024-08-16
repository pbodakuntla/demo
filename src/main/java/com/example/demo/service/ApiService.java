package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {

    @Autowired
    private RestTemplate restTemplate;

    private final String baseUrl = "http://localhost:8999";

    public String getPosts() {
        String url = baseUrl + "/hello";
        String response = restTemplate.getForObject(url, String.class);
        		System.out.println(response);
        		
        return response;
    }

    // Add more methods to interact with the API as needed
}