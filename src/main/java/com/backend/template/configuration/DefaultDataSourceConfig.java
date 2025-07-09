package com.backend.template.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties; // JpaProperties 임포트
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap; // HashMap 임포트
import java.util.Map; // Map 임포트
import java.util.Properties;

@Configuration
public class DefaultDataSourceConfig {

    private final JpaProperties jpaProperties; // JpaProperties 주입

    public DefaultDataSourceConfig(JpaProperties jpaProperties) {
        this.jpaProperties = jpaProperties;
    }

    @Bean
    @Primary // 여러 DataSource가 있을 경우 기본 DataSource로 설정
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public DataSource defaultDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean userEntityManagerFactory(DataSource defaultDataSource) {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(defaultDataSource);
        bean.setPackagesToScan("com.backend.template"); // 엔티티 패키지 스캔
        bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        // application.yml의 spring.jpa.properties를 가져와 설정합니다.
        // 여기에 database-platform (Dialect)도 포함됩니다.
        Map<String, Object> hibernateProperties = new HashMap<>(this.jpaProperties.getProperties());

        // DefaultJpaConfig에서 정의한 Naming Strategy를 추가합니다.
        // 기존 jpaNamingStrategy()는 Properties를 반환하므로 Map으로 변환하여 병합합니다.
        Properties namingProperties = DefaultJpaConfig.jpaNamingStrategy();
        namingProperties.forEach((key, value) -> hibernateProperties.put(key.toString(), value));

        bean.setJpaPropertyMap(hibernateProperties); // Map으로 설정

        return bean;
    }

    @Primary
    @Bean
    public PlatformTransactionManager userTransactionManager(
            @Qualifier("userEntityManagerFactory") LocalContainerEntityManagerFactoryBean userEntityManagerFactory
    ) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(userEntityManagerFactory.getObject());
        return transactionManager;
    }
}
