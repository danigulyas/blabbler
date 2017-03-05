package com.danigu.web.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author dani
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String getHome() {
        return "index";
    }
}
