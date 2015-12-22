package ru.sbertech.interview.app.configuration;

import java.io.IOException;
import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class HibernateConfiguration {
	
	private static final String PROPERTY_DATABASE_DRIVER = "db.driver";
	private static final String PROPERTY_DATABASE_CONNECTION_URL = "db.url";
	private static final String PROPERTY_DATABASE_USERNAME = "db.username";
	private static final String PROPERTY_DATABASE_PASSWORD = "db.password";
	
	private static final String PROPERTY_HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String PROPERTY_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	private static final String PROPERTY_HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
	
	private static final String SESSION_FACTORY_PACKAGES_TO_SCAN = "jpa.entity_manager.packages_to_scan"; 

	@Resource
	private Environment environment;
	
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
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }
	
	@Bean
	public SessionFactory sessionFactory() throws IOException {

		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setPackagesToScan(SESSION_FACTORY_PACKAGES_TO_SCAN);
	    sessionFactoryBean.setHibernateProperties(getHibernateProperties());
	    sessionFactoryBean.setDataSource(dataSource());
	    sessionFactoryBean.afterPropertiesSet();

	    return sessionFactoryBean.getObject();
		
	}
	
	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put(PROPERTY_HIBERNATE_DIALECT, environment.getRequiredProperty(PROPERTY_HIBERNATE_DIALECT));
		properties.put(PROPERTY_HIBERNATE_SHOW_SQL, environment.getRequiredProperty(PROPERTY_HIBERNATE_SHOW_SQL));
		properties.put(PROPERTY_HIBERNATE_HBM2DDL_AUTO, environment.getRequiredProperty(PROPERTY_HIBERNATE_HBM2DDL_AUTO));
        return properties;
	}

}
