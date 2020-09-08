package com.sh.zuulgateway.configuration;

import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZuulConfig {
    @Bean
    public PatternServiceRouteMapper serviceRouteMapper(){
        /**
         * A RegExp Pattern that extract needed information from a service ID. Ex :
         * "(?<name>.*)-(?<version>v.*$)"
         */
        //private Pattern servicePattern;
        /**
         * A RegExp that refer to named groups define in servicePattern. Ex :
         * "${version}/${name}"*/
        /*将-demo-v1映射为/v1/demo/***/
         return new PatternServiceRouteMapper("(?<name>^.+)-(?<version>v.+$)", "${version}/${name}");
    }
}
