package ru.sbertech.interview.app.configuration;

import java.io.InputStreamReader;
import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import ru.sbertech.interview.core.json.provider.JsonStringProvider;
import ru.sbertech.interview.core.json.provider.ReaderJsonStringProvider;

@Configuration
@ComponentScan("ru.sbertech.interview")
@PropertySource("classpath:application.properties")
public class ApplicationConfiguration {
	
	private static final String PROPERTY_DATABASE_DRIVER = "db.driver";
	private static final String PROPERTY_CONNECTION_URL = "db.url";
	private static final String PROPERTY_USERNAME = "db.username";
	private static final String PROPERTY_PASSWORD = "db.password";
	
	private static final String PROPERTY_HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String PROPERTY_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	private static final String PROPERTY_HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
	
	private static final String ENTITY_MANAGER_PACKAGES_TO_SCAN = "jpa.entity_manager.packages_to_scan"; 

	@Resource
	private Environment environment;
	
	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put(PROPERTY_HIBERNATE_DIALECT, environment.getRequiredProperty(PROPERTY_HIBERNATE_DIALECT));
		properties.put(PROPERTY_HIBERNATE_SHOW_SQL, environment.getRequiredProperty(PROPERTY_HIBERNATE_SHOW_SQL));
		properties.put(PROPERTY_HIBERNATE_HBM2DDL_AUTO, environment.getRequiredProperty(PROPERTY_HIBERNATE_HBM2DDL_AUTO));
		return properties;
	}
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		 
        dataSource.setDriverClassName(environment.getRequiredProperty(PROPERTY_DATABASE_DRIVER));
        dataSource.setUrl(environment.getRequiredProperty(PROPERTY_CONNECTION_URL));
        dataSource.setUsername(environment.getRequiredProperty(PROPERTY_USERNAME));
        dataSource.setPassword(environment.getRequiredProperty(PROPERTY_PASSWORD));
        
        return dataSource;
	}

	
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManager() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = 
				new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan(environment.getRequiredProperty(ENTITY_MANAGER_PACKAGES_TO_SCAN));
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
 
        entityManagerFactoryBean.setJpaProperties(getHibernateProperties());
        entityManagerFactoryBean.afterPropertiesSet();
        
        return entityManagerFactoryBean;	

	}
	
	@Bean
	public JsonStringProvider jsonStringProvider() {
		ReaderJsonStringProvider provider = new ReaderJsonStringProvider();
		provider.setReader(new InputStreamReader(System.in));
		return provider;
	}
	
	
}
