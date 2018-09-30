package de.kitaggmbhtrier.bistro.portal.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("de.kitaggmbhtrier.bistro.data")
@EnableJpaRepositories("de.kitaggmbhtrier.bistro.repository")
public class JpaConfig {

}
