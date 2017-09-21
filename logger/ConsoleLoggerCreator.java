package logger;

import java.util.Map;
import java.util.logging.Logger;

public class ConsoleLoggerCreator implements LoggerCreator {

	private static ConsoleLoggerCreator instance;
	
	private ConsoleLoggerCreator(){}
	
	public static ConsoleLoggerCreator getInstance(){
		if (instance == null){
			instance = new ConsoleLoggerCreator();
		}
		return instance;
	}
	
	@Override
	public ILogger getLogger(Map<String, String> dbParamsMap, Logger logger) {		
		return new ConsoleLogger();
	}

}
