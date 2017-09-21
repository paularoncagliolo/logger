package logger;

import java.io.File;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileLogger implements ILogger {

	private FileHandler fh;
	private Logger logger;
	
	public FileLogger(String folder, String filename, Logger logger) throws Exception {
		StringBuffer uri = new StringBuffer();		
		uri.append(folder);
		uri.append(filename);		
		File logFile = new File(uri.toString());
		if (!logFile.exists()) {
			logFile.createNewFile();
		}		
		this.fh = new FileHandler(uri.toString());
		this.logger = logger;
	}
	
	@Override
	public void log(String message) throws Exception {
		if(message.length() > 0) {
			logger.addHandler(this.fh);
			logger.log(Level.INFO, message.toString());
		}
	};
	
	@Override
	public boolean isDatabaseLogger() {
		return false;
	};

}


