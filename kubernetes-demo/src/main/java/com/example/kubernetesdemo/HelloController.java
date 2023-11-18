package com.example.kubernetesdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class HelloController {

    @Value("${hello.greeting}")
    private String greeting;

    @GetMapping(path = {"/hello", "/hello/{name}"})
    public String hello(@PathVariable(required = false) Optional<String> name) {
        return this.greeting + " " + name.orElse("World");
    }

    @GetMapping("/")
    public String greet() {
        return "Hello World";
    }
}