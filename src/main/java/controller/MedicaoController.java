package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.MedicaoService;

@RestController
@RequestMapping("/medicoes")
public class MedicaoController {

    @Autowired
    private MedicaoService medicaoService;


}
