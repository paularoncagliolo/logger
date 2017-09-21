package logger;

import java.util.Map;
import java.util.logging.Logger;

public class DatabaseLoggerCreator implements LoggerCreator {

	private static DatabaseLoggerCreator instance;
	
	private DatabaseLoggerCreator(){}
	
	public static DatabaseLoggerCreator getInstance(){
		if (instance == null){
			instance = new DatabaseLoggerCreator();
		}
		return instance;
	}
	
	@Override
	public ILogger getLogger(Map<String, String> dbParamsMap, Logger logger)throws Exception {	
		return new DatabaseLogger(
				dbParamsMap.get("dbms"), 
				dbParamsMap.get("serverName"), 
				dbParamsMap.get("portNumber"), 
				dbParamsMap.get("userName"),
				dbParamsMap.get("password"));
	}
}
