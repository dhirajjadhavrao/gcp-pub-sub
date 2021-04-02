package com.josh.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.stereotype.Component;

@Component
public class GCPConnectionManager {

    private static GCPConnectionManager instance = new GCPConnectionManager();

    @Autowired
    private PubSubTemplate pubSubTemplate;

    private GCPConnectionManager(){}

    public static GCPConnectionManager getInstance(){return instance;}

    public PubSubTemplate getTemplate(){ return pubSubTemplate; }
}
