package ru.sbertech.interview.app.configuration;

import java.io.InputStreamReader;
import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import ru.sbertech.interview.core.converter.JsonEntityConverter;
import ru.sbertech.interview.core.repository.dao.DaoEntityStoreRepository;
import ru.sbertech.interview.core.repository.file.FileEntityStoreRepository;
import ru.sbertech.interview.json.provider.JsonStringProvider;
import ru.sbertech.interview.json.provider.ReaderJsonStringProvider;

@Configuration
@ComponentScan("ru.sbertech.interview")
@Import(JpaConfiguration.class)
public class ApplicationConfiguration {
	
	@Bean
	public FileEntityStoreRepository fileEntityStoreRepository() {
		return new FileEntityStoreRepository();
	}
	
	@Bean
	public JsonEntityConverter entityConverter() {
		ReaderJsonStringProvider provider = new ReaderJsonStringProvider();
		provider.setReader(new InputStreamReader(System.in));
		JsonEntityConverter converter = new JsonEntityConverter(new ReaderJsonStringProvider());
		return converter;
	}
	
	@Bean
	public JsonStringProvider jsonStringProvider() {
		ReaderJsonStringProvider provider = new ReaderJsonStringProvider();
		provider.setReader(new InputStreamReader(System.in));
		return provider;
	}
	
}
