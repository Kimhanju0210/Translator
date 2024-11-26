package org.example.translator.trans.controller;

import org.example.translator.trans.service.TransService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/translate")
public class TransContoller {

    @Autowired
    private TransService transService;

    @PostMapping
    public Map<String, String> translate(@RequestBody Map<String, String> request) {
        String text = request.get("text");
        String from = request.get("from");
        String to = request.get("to");

        String translatedText = transService.translate(text, from, to);

        Map<String, String> response = new HashMap<>();
        response.put("translatedText", translatedText);
        return response;
    }
}
