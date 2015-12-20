package ru.sbertech.interview.configuration;

import java.io.InputStreamReader;

import org.springframework.context.annotation.Bean;

import ru.sbertech.interview.core.entity.converter.JsonEntityConverter;
import ru.sbertech.interview.core.entity.repository.file.FileEntityStoreRepository;
import ru.sbertech.interview.core.entity.repository.jpa.JpaEntityStoreRepository;
import ru.sbertech.interview.core.json.provider.ReaderJsonStringProvider;

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
