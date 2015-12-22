package ru.sbertech.interview.core.repository.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import ru.sbertech.interview.core.jpa.entity.ValueEntity;
import ru.sbertech.interview.core.jpa.entity.fabric.ValueEntityFabric;
import ru.sbertech.interview.core.value.ValueContainer;
import ru.sbertech.interview.store.exception.FailedSaveValueException;
import ru.sbertech.interview.store.repository.ValueStoreRepository;

@Service
public class FileEntityStoreRepository implements ValueStoreRepository {

	public static final String PROPERTY_OUTPUT_FILENAME = "output.filename";
	public static final String DEFAULT_PROPERTY_OUTPUT_FILENAME = "value.bin";
	
	@Autowired
	private ValueEntityFabric valueEntityFabric;
	
	@Override
	@Transactional
	public void save(Object value) throws FailedSaveValueException {
		
		Properties properties = System.getProperties();
		
		String filename = DEFAULT_PROPERTY_OUTPUT_FILENAME;
		
		if (properties.containsKey(PROPERTY_OUTPUT_FILENAME)) {
			filename = properties.getProperty(PROPERTY_OUTPUT_FILENAME);
		}
		
		File file = new File(filename);

		FileTransactionalListener transactionalListener = new FileTransactionalListener(file);
		TransactionSynchronizationManager.registerSynchronization(transactionalListener);
		
		ValueContainer container = (ValueContainer) value;
		
		ValueEntity entity = valueEntityFabric.createValueEntity(container);
		
		StringBuilder backupPath = new StringBuilder();
		backupPath
		.append(file.getPath())
		.append(".backup");
		
		File backupFile = new File(backupPath.toString());
		
		file.renameTo(backupFile);

		try (FileOutputStream fileStream = new FileOutputStream(file)) {
			ObjectOutputStream outputStream = new ObjectOutputStream(fileStream);
			outputStream.writeObject(entity);
		}
		catch (IOException ex) {
			
			StringBuilder exceptionMessageBuilder = new StringBuilder()
			.append("An IOException thrown: ")
			.append(ex.getMessage());
			
			throw new FailedSaveValueException(exceptionMessageBuilder.toString());
		}
		
	}

}
