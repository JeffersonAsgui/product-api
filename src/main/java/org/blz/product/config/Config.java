package org.blz.product.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan(basePackages = "org.blz.product.model")
@EnableJpaRepositories(basePackages = "org.blz.product.repository")
@EnableTransactionManagement
@EnableAsync
public class Config {
}
