package com.josh;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.cloud.gcp.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan("com.josh")
public class Subscriber {
    Logger logger = LoggerFactory.getLogger(Subscriber.class);
    public static void main(String[] args) {
        SpringApplication.run(Subscriber.class, args);
    }

    @Bean
    public PubSubInboundChannelAdapter messageChannelAdapter(@Qualifier("demoInputChannel") MessageChannel inputChannel, PubSubTemplate pubSubTemplate) {
        PubSubInboundChannelAdapter adapter = new PubSubInboundChannelAdapter(pubSubTemplate, "notify-app-sub");
        adapter.setOutputChannel(inputChannel);
        return adapter;
    }

    @Bean
    public MessageChannel demoInputChannel() {
        return new DirectChannel();
    }

    @ServiceActivator(inputChannel = "demoInputChannel")
            public void messageReceiver(String message) {
        logger.info("Message arrived --> " + message);
    }
}
