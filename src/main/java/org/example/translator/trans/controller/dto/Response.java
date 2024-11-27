package org.example.translator.trans.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private Message message;

    @AllArgsConstructor
    @Getter
    @NoArgsConstructor
    public static class Message {
        private Result result;
    }

    @AllArgsConstructor
    @Getter
    @NoArgsConstructor
    public static class Result {
        private String srcLangType;
        private String tarLangType;
        private String translatedText;
    }

}
