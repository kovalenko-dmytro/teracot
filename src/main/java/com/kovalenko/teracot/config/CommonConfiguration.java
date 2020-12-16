package com.kovalenko.teracot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class CommonConfiguration {

    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer properties =
            new PropertySourcesPlaceholderConfigurer();
        properties.setLocations(
            new FileSystemResource("C:\\java\\projects\\teracotConfig\\teracot.properties"),
            new FileSystemResource("C:\\java\\projects\\teracotConfig\\cs-template.properties"));
        properties.setIgnoreResourceNotFound(false);
        return properties;
    }
}
