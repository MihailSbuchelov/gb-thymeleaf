package ru.gb.gbthymeleaf.config;

import lombok.Value;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Driver;
import java.util.Optional;
import java.util.Properties;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditAwareBean")
@ComponentScan("ru.gb")
@EnableTransactionManagement
@EnableJpaRepositories("ru.gb.gbthymeleaf.dao")
public class ShopConfig {

    @Bean
    public AuditorAware<String> auditAwareBean() {
        return () -> Optional.of("User");
    }
}
