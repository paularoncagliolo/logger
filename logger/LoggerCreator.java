package logger;

import java.util.Map;
import java.util.logging.Logger;

public interface LoggerCreator {

	public ILogger getLogger(Map<String, String> dbParamsMap, Logger logger) throws Exception;

}
