package org.example.translator.trans.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class TransService {
    private final String API_URL = "https://api.example.com/translate";  // Replace with actual API URL

    public String translate(String text, String from, String to) {
        RestTemplate restTemplate = new RestTemplate();

        // Create the request payload
        Map<String, String> request = new HashMap<>();
        request.put("text", text);
        request.put("from", from);
        request.put("to", to);

        // Make the API request
        Map<String, String> response = restTemplate.postForObject(API_URL, request, Map.class);
        return response != null ? response.get("translatedText") : "";
    }
}
