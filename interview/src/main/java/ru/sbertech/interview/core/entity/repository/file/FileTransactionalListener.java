package ru.sbertech.interview.core.entity.repository.file;

import java.io.File;

import org.springframework.transaction.support.TransactionSynchronizationAdapter;

public class FileTransactionalListener extends TransactionSynchronizationAdapter  {
	
	private File file;
	
	public FileTransactionalListener(File file) {
		this.file = file;
	}
	
	
	
	@Override
	public void afterCompletion(int status) {
		
		if (status == STATUS_ROLLED_BACK
				|| status == STATUS_COMMITTED) {

			StringBuilder backupedPath = new StringBuilder();
			
			backupedPath
			.append(file.getPath())
			.append(".backup");
			File backupedFile = new File(backupedPath.toString());
			
			if (status == STATUS_ROLLED_BACK
					&& backupedFile.exists() && backupedFile.isFile()) {				
				if (file.delete()) {
					backupedFile.renameTo(file);
				}
			}

			if (status == STATUS_COMMITTED) {				
				backupedFile.delete();
			}
			
		}
		
	}
	
}
