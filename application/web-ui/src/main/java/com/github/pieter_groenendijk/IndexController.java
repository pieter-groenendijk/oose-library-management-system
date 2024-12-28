package src.main.java.com.github.pieter_groenendijk;

import org.springframework.web.bind.annotation.GetMapping;

public class IndexController {
    @GetMapping("/")
    public String indexPage() {

        return "index";
    }
}
