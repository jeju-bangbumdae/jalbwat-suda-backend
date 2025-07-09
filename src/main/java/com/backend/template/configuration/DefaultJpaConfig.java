//package com.backend.template.configuration;
//
//import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
//import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
//import java.util.Properties;
//
//public class DefaultJpaConfig {
//
//    public static Properties jpaNamingStrategy() {
//        Properties properties = new Properties();
//        properties.put("hibernate.physical_naming_strategy", CamelCaseToUnderscoresNamingStrategy.class.getName());
//        properties.put("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName());
//        return properties;
//    }
//}
