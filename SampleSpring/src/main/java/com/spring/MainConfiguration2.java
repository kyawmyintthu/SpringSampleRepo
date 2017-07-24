package com.spring;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.ejb.HibernatePersistence;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;


@Configuration
@ComponentScan(basePackages = "com.spring")
@PropertySource("classpath:application.properties")
@EnableJpaRepositories(
	    basePackages = "com.spring.data2", 
	    entityManagerFactoryRef = "productEntityManager", 
	    transactionManagerRef = "productTransactionManager"
	)
public class MainConfiguration2 {

	private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
	private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
	private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
	private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";
	
	private static final String PROPERTY_NAME_DATABASE_DRIVER2 = "db.driver2";
	private static final String PROPERTY_NAME_DATABASE_PASSWORD2 = "db.password2";
	private static final String PROPERTY_NAME_DATABASE_URL2 = "db.url2";
	private static final String PROPERTY_NAME_DATABASE_USERNAME2 = "db.username2";

	private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
	private static final String PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY = "hibernate.ejb.naming_strategy";
	private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";
	private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN2 = "entitymanager.packages.to.scan2";

	private static final String PROPERTY_NAME_MESSAGESOURCE_BASENAME = "message.source.basename";
	private static final String PROPERTY_NAME_MESSAGESOURCE_USE_CODE_AS_DEFAULT_MESSAGE = "message.source.use.code.as.default.message";

	@Resource
	private Environment environment;
	
	@Bean
	public DataSource dataSource2() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		dataSource.setDriverClassName(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER2));
        dataSource.setUrl(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_URL2));
        dataSource.setUsername(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME2));
        dataSource.setPassword(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD2));
		return dataSource;
	}

	
	@Bean
	public JpaTransactionManager productTransactionManager() throws ClassNotFoundException {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(productEntityManager().getObject());
		return transactionManager;
	}
	

	 private Properties hibProperties() {
	        Properties properties = new Properties();
	        properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
	        properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
	        return properties;
	    }
	 
	 @Bean
	    public LocalContainerEntityManagerFactoryBean productEntityManager() {
	        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
	        entityManagerFactoryBean.setDataSource(dataSource2());
	        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistence.class);
	        entityManagerFactoryBean.setPackagesToScan(environment.getRequiredProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN2));
	        entityManagerFactoryBean.setJpaProperties(hibProperties());
	        return entityManagerFactoryBean;
	    }

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

		messageSource.setBasename(environment.getRequiredProperty(PROPERTY_NAME_MESSAGESOURCE_BASENAME));
		messageSource.setUseCodeAsDefaultMessage(Boolean.parseBoolean(
				environment.getRequiredProperty(PROPERTY_NAME_MESSAGESOURCE_USE_CODE_AS_DEFAULT_MESSAGE)));

		return messageSource;
	}

}
