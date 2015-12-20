package ru.sbertech.interview.core.entity.repository.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import ru.sbertech.interview.InterviewApplication;
import ru.sbertech.interview.entity.repository.EntityStoreRepository;

public class FileEntityStoreRepository implements EntityStoreRepository {

	@Transactional
	public void save(Object entity) throws Exception {
		
		Properties properties = System.getProperties();
		
		String filename = InterviewApplication.DEFAULT_PROPERTY_OUTPUT_FILENAME;
		
		if (properties.containsKey(InterviewApplication.PROPERTY_OUTPUT_FILENAME)) {
			filename = properties.getProperty(InterviewApplication.PROPERTY_OUTPUT_FILENAME);
		}
		
		File file = new File(filename);

		FileTransactionalListener transactionalListener = new FileTransactionalListener(file);
		TransactionSynchronizationManager.registerSynchronization(transactionalListener);
		
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
		catch (Exception ex) {
			throw ex;
		}
		
	}

}
