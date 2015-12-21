package ru.sbertech.interview.core.entity.repository.file;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import ru.sbertech.interview.core.entity.ValueEntity;
import ru.sbertech.interview.core.entity.target.TargetType;
import ru.sbertech.interview.core.repository.file.FileEntityStoreRepository;

public class TestFileEntityStoreRepository {

	@Test
	@Transactional
	public void testSave() throws Exception {
		
		FileEntityStoreRepository repository = new FileEntityStoreRepository();
		
		ValueEntity entity = new ValueEntity();
		entity.setTargetType(TargetType.FILE);
		entity.setValue("stringValue");
		
		repository.save(entity);
		
		File file = new File(FileEntityStoreRepository.DEFAULT_PROPERTY_OUTPUT_FILENAME);

		FileInputStream fileStream = new FileInputStream(file); 
		ObjectInputStream inputStream = new ObjectInputStream(fileStream);
		ValueEntity readEntity = (ValueEntity) inputStream.readObject();
		fileStream.close();
		
		assertTrue(readEntity.getTargetType().equals(entity.getTargetType()));
		assertTrue(readEntity.getValue().toString().equals(entity.getValue().toString()));
		
	}

}
