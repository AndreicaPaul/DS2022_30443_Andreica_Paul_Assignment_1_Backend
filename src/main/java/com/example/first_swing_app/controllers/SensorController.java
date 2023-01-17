package com.example.first_swing_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class SensorController {

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/hello")
    @RequestMapping(path="/message", method=POST)
    public void sendAlert(String message) {
        this.template.convertAndSend("/alerts/message", message);
    }

}