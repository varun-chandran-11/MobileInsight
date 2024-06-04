package com.varun.mobile.insight.util;

import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@EnableMongoAuditing
public class MongoConfig {

    public AuditorAware<String> auditorProvider() {
        return () -> Optional.of("defaultUser");
    }

}