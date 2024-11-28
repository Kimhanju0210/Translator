package org.example.translator.trans.service;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ConsoleService {
    @Value("${mymemory.api-url}")
    private String myMemoryApiUrl;

    private final RestTemplate restTemplate;

    public String translate(String text, String sourceLang, String targetLang) {
        try {
            String url = myMemoryApiUrl + "?q=" + URLEncoder.encode(text, StandardCharsets.UTF_8)
                    + "&langpair=" + sourceLang + "|" + targetLang
                    + "&only=machine";
            System.out.println("API 요청 URL: " + url);

            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

            System.out.println("API 응답 상태 코드: " + response.getStatusCodeValue());
            System.out.println("API 응답 내용: " + response.getBody());

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                Map<String, Object> responseBody = response.getBody();
                if (responseBody.containsKey("responseData")) {
                    Map<String, Object> responseData = (Map<String, Object>) responseBody.get("responseData");
                    return (String) responseData.get("translatedText");
                }
            }
        } catch (Exception e) {
            System.err.println("번역 중 오류 발생: " + e.getMessage());
        }
        return "번역 실패: 요청이 올바르지 않거나 제한을 초과했습니다.";
    }

}
