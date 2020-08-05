package com.svetakvetko.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/")
@Controller
public class StartedPageController {

    @GetMapping("/")
    public String sendStartedPage() {
        return "index";
    }

}
