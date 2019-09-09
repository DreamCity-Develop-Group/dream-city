package com.dream.city.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.server.ServerEndpoint;

/**
 * @author Wvv
 */
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter getServer(){
        return new ServerEndpointExporter();
    }
}
