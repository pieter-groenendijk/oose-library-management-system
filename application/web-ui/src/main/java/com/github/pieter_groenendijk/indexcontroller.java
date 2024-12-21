package src.main.java.com.github.pieter_groenendijk;

import org.springframework.web.bind.annotation.GetMapping;

public class indexcontroller {
    @GetMapping("/")
    public String indexPage() {

        return "index";
    }
}
