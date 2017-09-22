package com.botham.rest.jobs;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

// http://www.baeldung.com/spring-data-jpa-multiple-databases
	
@Configuration
@PropertySource({ "classpath:db.properties" })
@EnableJpaRepositories(basePackages = "com.botham.news.db.jobs", 
                       entityManagerFactoryRef = "jobsEntityManager", 
                       transactionManagerRef = "jobsTransactionManager"
)
public class JobsConfig {
    @Autowired
    private Environment env;
     
    @Bean
    public LocalContainerEntityManagerFactoryBean jobsEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(jobsDataSource());
        em.setPackagesToScan("com.botham.news.domain");
 
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("news.hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", env.getProperty("news.hibernate.dialect"));
        properties.put("hibernate.show_sql", env.getProperty("news.show_sql"));
        em.setJpaPropertyMap(properties);
 
        return em;
    }
        
    @Bean
    public DataSource jobsDataSource() {
  
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("news.jdbc.driver-class-name"));
        dataSource.setUrl(env.getProperty("news.jdbc.url"));
        dataSource.setUsername(env.getProperty("news.jdbc.user"));
        dataSource.setPassword(env.getProperty("news.jdbc.password"));
        return dataSource;
    }
 
    @Bean
    public PlatformTransactionManager jobsTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(jobsEntityManager().getObject());
        return transactionManager;
    }    
}
