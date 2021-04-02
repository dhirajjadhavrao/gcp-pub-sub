package com.josh.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

@Service
public class ReceiverService {

    public JsonNode receiveMessage(JsonNode message){
        return message;
    }
}
