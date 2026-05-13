package com.kiyan.weathereggapi.mqtt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
@Profile("!test")
@EnableIntegration
public class MqttConfig {
    @Value("${MQTT_BROKER_URL}")
    private String BROKER_URL;

    @Value("${MQTT_BROKER_CLIENTID}")
    private String CLIENT_ID;

    @Value("${MQTT_TOPIC}")
    private String TOPIC;

    // Inbound configuration
    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(
                BROKER_URL, CLIENT_ID + "-inbound", TOPIC);
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    // Outbound configuration
    @Bean
    public MessageChannel mqttOutputChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutputChannel")
    public MessageHandler outbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(
                BROKER_URL, CLIENT_ID + "-outbound"); // TODO: Give a different broker ID
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic("plant/water");
        return messageHandler;
    }

    @MessagingGateway(defaultRequestChannel = "mqttOutputChannel")
    public interface MqttOutputChannel {
        void sendToTopic(String message);
    }
}