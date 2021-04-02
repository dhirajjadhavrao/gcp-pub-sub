package com.josh.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

@Service
public class PublishService {

    public JsonNode publishMessage(JsonNode message){
        return message;
    }
}
