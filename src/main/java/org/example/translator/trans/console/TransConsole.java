package org.example.translator.trans.console;

import lombok.RequiredArgsConstructor;
import org.example.translator.trans.service.ConsoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class TransConsole implements CommandLineRunner {

    private final ConsoleService consoleService;

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("번역할 텍스트를 입력하세요:");
        String textToTranslate = scanner.nextLine();
        System.out.println("입력된 텍스트: " + textToTranslate);

        System.out.println("원본 언어 (예: 'en' 또는 'ko')를 입력하세요:");
        String sourceLang = scanner.nextLine().toLowerCase();
        System.out.println("입력된 원본 언어: " + sourceLang);

        System.out.println("번역할 언어 (예: 'en' 또는 'ko')를 입력하세요:");
        String targetLang = scanner.nextLine().toLowerCase();
        System.out.println("입력된 대상 언어: " + targetLang);

        String translatedText = consoleService.translate(textToTranslate, sourceLang, targetLang);

        if (translatedText != null) {
            System.out.println("번역 결과: " + translatedText);
        } else {
            System.out.println("번역에 실패했습니다.");
        }
    }

}
