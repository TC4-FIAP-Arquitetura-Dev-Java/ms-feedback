package com.ms.feedback.infrastrcture.config.mongodb;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.OffsetDateTime;
import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class MongoConfig {

    public DateTimeProvider dataTimeProvider() {
        return () -> Optional.of(OffsetDateTime.now());
    }
}
