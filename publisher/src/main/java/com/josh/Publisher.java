package com.josh;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.cloud.gcp.pubsub.integration.outbound.PubSubMessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.josh")
public class Publisher {
    Logger logger = LoggerFactory.getLogger(Publisher.class);
    public static void main(String[] args) {
        SpringApplication.run(Publisher.class, args);
    }

    @Bean
    @ServiceActivator(inputChannel = "demoOutputChannel")
    public MessageHandler messageSender(PubSubTemplate pubsubTemplate) {
        return new PubSubMessageHandler(pubsubTemplate, "notify-app");
    }

    @MessagingGateway(defaultRequestChannel = "demoOutputChannel")
    public interface PubsubOutboundGateway {
        void sendToPubsub(String text);
    }
}
