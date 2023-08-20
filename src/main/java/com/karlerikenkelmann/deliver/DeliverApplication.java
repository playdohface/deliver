package com.karlerikenkelmann.deliver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication
public class DeliverApplication implements ErrorController {

    public static void main(String[] args) {
        SpringApplication.run(DeliverApplication.class, args);
    }

    //We're forwarding all Errors to Angular
    @RequestMapping(value = "/error")
    public String error() {
        return "forward:/index.html";
    }
}
