package org.example.spring_realization_transactions.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/licabinet")
@Slf4j
public class LiCabinetController {

    @GetMapping
    public String licabinetPage() {
        log.info("Переход на страницу личного кабинета");
        return "/licabinet";
    }
}
