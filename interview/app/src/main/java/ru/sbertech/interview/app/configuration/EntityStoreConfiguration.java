package ru.sbertech.interview.app.configuration;

import java.io.InputStreamReader;

import org.springframework.context.annotation.Bean;

import ru.sbertech.interview.core.converter.JsonEntityConverter;
import ru.sbertech.interview.core.json.provider.ReaderJsonStringProvider;
import ru.sbertech.interview.core.repository.file.FileEntityStoreRepository;
import ru.sbertech.interview.core.repository.jpa.JpaEntityStoreRepository;

public class EntityStoreConfiguration {
	
	@Bean
	public JpaEntityStoreRepository daoEntityStoreRepository() {
		return new JpaEntityStoreRepository();
	}
	
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

}
