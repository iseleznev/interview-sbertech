package ru.sbertech.interview.core.value.repository.file;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import ru.sbertech.interview.core.value.ValueContainer;
import ru.sbertech.interview.core.value.repository.file.FileValueService;
import ru.sbertech.interview.core.value.target.TargetType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, 
                      classes = ApplicationContextTestConfiguration.class)
public class TestFileValueService {

	@Test
	@Transactional
	public void testSave() throws Exception {

		/*FileValueStoreRepository repository = new FileValueStoreRepository();
		
		ValueContainer entity = new ValueContainer();
		entity.setTargetType(TargetType.FILE);
		entity.setValue("stringValue");
		
		repository.save(entity);
		
		File file = new File(FileValueStoreRepository.DEFAULT_PROPERTY_OUTPUT_FILENAME);

		FileInputStream fileStream = new FileInputStream(file); 
		ObjectInputStream inputStream = new ObjectInputStream(fileStream);
		ValueContainer readEntity = (ValueContainer) inputStream.readObject();
		fileStream.close();
		
		assertTrue(readEntity.getTargetType().equals(entity.getTargetType()));
		assertTrue(readEntity.getValue().toString().equals(entity.getValue().toString()));*/
		
	}

}
