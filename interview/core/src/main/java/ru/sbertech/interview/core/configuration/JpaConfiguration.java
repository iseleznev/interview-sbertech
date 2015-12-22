package ru.sbertech.interview.core.configuration;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("ru.sbertech.interview.core.jpa")
public class JpaConfiguration {
	
	private static final String PROPERTY_DATABASE_DRIVER = "db.driver";
	private static final String PROPERTY_DATABASE_CONNECTION_URL = "db.url";
	private static final String PROPERTY_DATABASE_USERNAME = "db.username";
	private static final String PROPERTY_DATABASE_PASSWORD = "db.password";
	
	private static final String PROPERTY_HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String PROPERTY_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	private static final String PROPERTY_HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
	
	private static final String ENTITY_MANAGER_PACKAGES_TO_SCAN = "ru.sbertech.interview.core.jpa"; 

	@Resource
	private Environment environment;
	
	@Bean(name = "transactionManager")
	public JpaTransactionManager getTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject()); 
        return transactionManager;		
	}
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty(PROPERTY_DATABASE_DRIVER));
        dataSource.setUrl(environment.getRequiredProperty(PROPERTY_DATABASE_CONNECTION_URL));
        dataSource.setUsername(environment.getRequiredProperty(PROPERTY_DATABASE_USERNAME));
        dataSource.setPassword(environment.getRequiredProperty(PROPERTY_DATABASE_PASSWORD));        
        return dataSource;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan(environment.getRequiredProperty(ENTITY_MANAGER_PACKAGES_TO_SCAN));
 
        entityManagerFactoryBean.setJpaProperties(getHibernateProperties());
        entityManagerFactoryBean.afterPropertiesSet();
        
        return entityManagerFactoryBean;	
    }
	
	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put(PROPERTY_HIBERNATE_DIALECT, environment.getRequiredProperty(PROPERTY_HIBERNATE_DIALECT));
		properties.put(PROPERTY_HIBERNATE_SHOW_SQL, environment.getRequiredProperty(PROPERTY_HIBERNATE_SHOW_SQL));
		properties.put(PROPERTY_HIBERNATE_HBM2DDL_AUTO, environment.getRequiredProperty(PROPERTY_HIBERNATE_HBM2DDL_AUTO));
        return properties;
	}
	

}
