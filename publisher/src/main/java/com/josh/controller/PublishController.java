package com.josh.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.josh.Publisher;
import com.josh.service.PublishService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublishController {
    Logger logger = LoggerFactory.getLogger(PublishController.class);

    @Autowired
    private Publisher.PubsubOutboundGateway messagingGateway;
    @Autowired
    private PublishService publishService;

    @PostMapping("/publish")
    public JsonNode publishMessage(@RequestBody JsonNode message){
        logger.info("Message : "+message.toString());
        messagingGateway.sendToPubsub(message.toString());
        return publishService.publishMessage(message);
    }
}
