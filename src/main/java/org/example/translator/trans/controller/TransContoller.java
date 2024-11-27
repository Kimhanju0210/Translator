package org.example.translator.trans.controller;

import lombok.RequiredArgsConstructor;
import org.example.translator.trans.service.TransService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/translate")
@RequiredArgsConstructor
public class TransContoller {

    private final TransService transService;

    @GetMapping("/home")
    public String home(Model model) {
        return "transhome";
    }

    @PostMapping("/api")
    public Map<String, String> translate(@RequestBody Map<String, String> request) {
        String text = request.get("text");
        String from = request.get("from");
        String to = request.get("to");

        Map<String, String> languageMap = new HashMap<>();
        languageMap.put("한국어", "ko");
        languageMap.put("영어", "en");
        languageMap.put("일본어", "ja");
        languageMap.put("중국어(간체)", "zh-CN");
        languageMap.put("스페인어", "es");
        languageMap.put("러시아어", "ru");
        languageMap.put("아랍어", "ar");
        languageMap.put("이탈리아어", "it");
        languageMap.put("프랑스어", "fr");
        languageMap.put("독일어", "de");

        String fromCode = languageMap.getOrDefault(from, "ko");
        String toCode = languageMap.getOrDefault(to, "en");

        String translatedText = transService.translate(text, fromCode, toCode);

        Map<String, String> response = new HashMap<>();
        response.put("translatedText", translatedText);
        return response;
    }
}
