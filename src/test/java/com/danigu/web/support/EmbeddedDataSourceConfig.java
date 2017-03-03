package com.danigu.web.support;

import com.danigu.web.config.PersistenceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * @author dani
 */
@Configuration
public class EmbeddedDataSourceConfig {

    /**
     * Counterpart to the "dev" profile one returned by {@link PersistenceConfig}.
     * We don't need to add any schemas, hibernate has automatic schema generation.
     * @return a configured embedded dataSource for testing.
     */
    @Bean
    @Primary
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }
}
