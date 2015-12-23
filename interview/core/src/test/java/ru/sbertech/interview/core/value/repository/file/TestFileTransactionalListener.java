package ru.sbertech.interview.core.value.repository.file;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.support.TransactionSynchronization;

import ru.sbertech.interview.core.value.repository.file.FileTransactionalListener;

public class TestFileTransactionalListener {

	private static final String TESTCASE_FILENAME = "testCaseFileTransactionalListener.txt"; 
	private static final String TESTCASE_BACKUP_FILENAME = "testCaseFileTransactionalListener.txt.backup"; 
	
	private FileTransactionalListener listener;
	private File file;
	private File backupFile;
	
	@Before
	public void setUp() throws IOException {
		
		file = writeFile(TESTCASE_FILENAME, new byte[] { 1 });
		backupFile = writeFile(TESTCASE_BACKUP_FILENAME, new byte[] { 1, 2 });
		
		listener = new FileTransactionalListener(file);
	}

	@Test
	public void testAfterCompletionRolledBack() {		

		assertTrue(file.exists());
		assertTrue(backupFile.exists());

		listener.afterCompletion(TransactionSynchronization.STATUS_ROLLED_BACK);

		assertTrue(file.exists());
		assertFalse(backupFile.exists());
		assertEquals(file.length(), 2);
		
	}

	@Test
	public void testAfterCompletionRolledBackBackupFailed() throws IOException {		

		file = writeFile(TESTCASE_FILENAME, new byte[] { 1, 2 });
		backupFile.delete();
		assertTrue(file.exists());
		assertFalse(backupFile.exists());

		listener.afterCompletion(TransactionSynchronization.STATUS_ROLLED_BACK);

		assertTrue(file.exists());
		assertFalse(backupFile.exists());
		assertEquals(file.length(), 2);
		
	}

	@Test
	public void testAfterCompletionCommitted() {
		
		assertTrue(file.exists());
		assertTrue(backupFile.exists());

		listener.afterCompletion(TransactionSynchronization.STATUS_COMMITTED);
		
		assertTrue(file.exists());
		assertFalse(backupFile.exists());
		assertEquals(file.length(), 1);
		
	}
	
	private File writeFile(String filename, byte[] buffer) throws IOException {
		
		StringBuilder fullPath = new StringBuilder();
		
		fullPath
		.append(System.getProperty("user.dir"))
		.append("/")
		.append(filename);
		
		File file = new File(fullPath.toString());
		
		try (FileOutputStream outputStream = new FileOutputStream(file)) {
			outputStream.write(buffer);
			outputStream.close();

		}
		catch (IOException ioex) {
			throw ioex;
		}
		
		return file;
		
	}

}
