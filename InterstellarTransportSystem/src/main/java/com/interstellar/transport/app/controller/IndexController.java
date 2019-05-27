package com.interstellar.transport.app.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {
	
	@GetMapping(path = "/", produces = "text/html")
    public String landingPage(Map<String, Object> model) {
        model.put("message", "Please enter travell details  !!");
        return "TravellerLandingPage";
    }
	
	@GetMapping(path = "/Search", produces = "text/html")
    public String home(Map<String, Object> model) {
        model.put("message", "Please enter travell details  !!");
        return "Index";
    }
}