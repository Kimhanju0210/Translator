package org.example.translator.trans.service;

import org.example.translator.trans.controller.dto.Response;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URL;
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
        WebClient webClient = WebClient.builder()
                .baseUrl(API_URL)
                .build();

        Response response = webClient.post()
                .uri(uriBuilder -> uriBuilder.queryParam("source", source)
                        .queryParam("target", target)
                        .queryParam("text", text)
                        .build())
                .header("X-Naver-Client-Id", clientId)
                .header("X-Naver-Client-Secret", clientSecret)
                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .retrieve()
                .bodyToMono(Response.class)
                .block();

        System.out.println("Response from API: " + response);

        return response != null ? response.getMessage().getResult().getTranslatedText() : "번역에 실패했습니다.";
    }

}
