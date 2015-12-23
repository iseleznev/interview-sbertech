package ru.sbertech.interview.app.configuration;

import java.io.InputStreamReader;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import ru.sbertech.interview.core.configuration.JpaConfiguration;
import ru.sbertech.interview.core.converter.JsonValueConverter;
import ru.sbertech.interview.core.value.repository.file.FileValueStoreRepository;
import ru.sbertech.interview.json.provider.JsonStringProvider;
import ru.sbertech.interview.json.provider.ReaderJsonStringProvider;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan({"ru.sbertech.interview.store", "ru.sbertech.interview.json", "ru.sbertech.interview.core"})
@Import(JpaConfiguration.class)
public class ApplicationConfiguration {
	
	@Bean
	public FileValueStoreRepository fileEntityStoreRepository() {
		return new FileValueStoreRepository();
	}
	
	@Bean
	public JsonValueConverter entityConverter() {
		ReaderJsonStringProvider provider = (ReaderJsonStringProvider) jsonStringProvider();		
		JsonValueConverter converter = new JsonValueConverter(provider);
		return converter;
	}
	
	@Bean
	public JsonStringProvider jsonStringProvider() {
		ReaderJsonStringProvider provider = new ReaderJsonStringProvider();
		provider.setReader(new InputStreamReader(System.in));
		return provider;
	}
	
}
