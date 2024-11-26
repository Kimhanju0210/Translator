package org.example.translator.trans.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

@Service
public class TransService {

    @Value("${naver.client.id}")
    private String clientId;

    @Value("${naver.client.secret}")
    private String clientSecret;

    private final String API_URL = "https://openapi.naver.com/v1/papago/n2mt";  // 파파고 번역 API URL

    public String translate(String text, String source, String target) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", clientId);
        headers.set("X-Naver-Client-Secret", clientSecret);
        headers.set("Content-Type", "application/x-www-form-urlencoded");

        Map<String, String> body = new HashMap<>();
        body.put("source", source);
        body.put("target", target);
        body.put("text", text);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);
        ResponseEntity<Map> response = restTemplate.exchange(API_URL, HttpMethod.POST, entity, Map.class);

        if (response.getBody() != null && response.getBody().containsKey("message")) {
            Map<String, Object> message = (Map<String, Object>) response.getBody().get("message");
            Map<String, String> result = (Map<String, String>) message.get("result");
            return result.get("translatedText");
        }

        return "";
    }

}
