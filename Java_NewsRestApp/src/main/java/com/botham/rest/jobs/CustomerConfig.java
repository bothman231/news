package com.botham.rest.jobs;

import java.util.HashMap;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource({ "classpath:db.properties" })
@EnableJpaRepositories(basePackages = "com.botham.news.db", 
                       entityManagerFactoryRef = "customerEntityManager", 
                       transactionManagerRef = "customerTransactionManager"
)
public class CustomerConfig {
    @Autowired
    private Environment env;
     
    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean customerEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(customerDataSource());
        em.setPackagesToScan(new String[] { "com.botham.news.domain" });
 
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("post.hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", env.getProperty("post.hibernate.dialect"));
        em.setJpaPropertyMap(properties);
 
        return em;
    }
    
    
    @Primary
    @Bean
    public DataSource customerDataSource() {
  
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("post.jdbc.driver-class-name"));
        dataSource.setUrl(env.getProperty("post.jdbc.url"));
        dataSource.setUsername(env.getProperty("post.jdbc.user"));
        dataSource.setPassword(env.getProperty("post.jdbc.password"));
        return dataSource;
    }
 
    @Primary
    @Bean
    public PlatformTransactionManager customerTransactionManager() {
  
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(customerEntityManager().getObject());
        return transactionManager;
    }

    
    
    
}
