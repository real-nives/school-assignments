package com.landon.hotel.controller;

import com.landon.hotel.service.WelcomeMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WelcomeController {
    
    @Autowired
    private WelcomeMessageService welcomeMessageService;
    
    @GetMapping("/welcome")
    public List<String> getWelcomeMessages() {
        return welcomeMessageService.getWelcomeMessages();
    }
} 