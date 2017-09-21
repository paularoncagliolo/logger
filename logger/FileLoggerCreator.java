package logger;

import java.util.Map;
import java.util.logging.Logger;

public class FileLoggerCreator implements LoggerCreator {

	private static FileLoggerCreator instance;
	
	private FileLoggerCreator(){}
	
	public static FileLoggerCreator getInstance(){
		if (instance == null){
			instance = new FileLoggerCreator();
		}
		return instance;
	}

	@Override
	public ILogger getLogger(Map<String, String> dbParamsMap, Logger logger) throws Exception {
		return new FileLogger(
				dbParamsMap.get("logFileFolder"),
				dbParamsMap.get("fileName"),
				logger);
	}

}
