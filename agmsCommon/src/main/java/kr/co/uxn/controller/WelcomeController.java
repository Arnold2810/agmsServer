package kr.co.uxn.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/admin/v1_0")
@RequiredArgsConstructor
public class WelcomeController {
    @GetMapping
    public String welcome(){
        return "HELLO SPRING";
    }

}