package controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/get")
public class TesteController {

    @GetMapping("/teste")
    public String teste() {
        return "Teste de API!";
    }
}
