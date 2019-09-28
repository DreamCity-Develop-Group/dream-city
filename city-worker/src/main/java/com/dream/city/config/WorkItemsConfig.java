package com.dream.city.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author WWvv
 */

@ConfigurationProperties(value = "worker.channel")
@Component
@Data
public class WorkItemsConfig {
    @Value("#{'${worker.channel.topics}'.split(',')}")
    private List<String> topics;

    @Value("#{${tasks}}")
    private Map<String,String> tasks;


}
