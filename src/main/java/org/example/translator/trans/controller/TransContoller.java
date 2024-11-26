package org.example.translator.trans.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/translate")
public class TransContoller {

    @PostMapping
    public Map<String, String> translate(@RequestBody Map<String, String> request) {
        String text = request.get("text");
        String from = request.get("from");
        String to = request.get("to");

        Map<String, String> response = new HashMap<>();
        return response;
    }
}
