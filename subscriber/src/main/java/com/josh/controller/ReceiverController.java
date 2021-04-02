package com.josh.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.josh.service.ReceiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReceiverController {

    @Autowired
    private ReceiverService receiverService;

    @PostMapping("/subscribe")
    public JsonNode receiveMessage(@RequestBody JsonNode message){
        return receiverService.receiveMessage(message);
    }
}
