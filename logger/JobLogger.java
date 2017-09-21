package logger;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class JobLogger {

	private static boolean logToFile;
	private static boolean logToConsole;
	private static boolean logMessage;
	private static boolean logWarning;
	private static boolean logError;
	private static boolean logToDatabase;

	private static List<ILogger> loggers;

	/**
	 * 
	 * @param logToFileParam
	 * @param logToConsoleParam
	 * @param logToDatabaseParam
	 * @param logMessageParam
	 * @param logWarningParam
	 * @param logErrorParam
	 * @param dbParamsMap
	 * @throws Exception
	 */
	public JobLogger(boolean logToFileParam, boolean logToConsoleParam, boolean logToDatabaseParam,
			boolean logMessageParam, boolean logWarningParam, boolean logErrorParam, Map<String, String> dbParamsMap) throws Exception {
		
		if (!logToConsole && !logToFile && !logToDatabase) {
			throw new Exception("Invalid configuration");
		}
		if (!logError && !logMessage && !logWarning) {
			throw new Exception("Error or Warning or Message must be specified");
		}
				
		logError = logErrorParam;
		logMessage = logMessageParam;
		logWarning = logWarningParam;

		loggers = new ArrayList<ILogger>();
		if (logToConsole) {
			loggers.add(ConsoleLoggerCreator.getInstance().getLogger(dbParamsMap, Logger.getLogger("MyLog")));
		}
		if (logToFile) {
			loggers.add(FileLoggerCreator.getInstance().getLogger(dbParamsMap, Logger.getLogger("MyLog")));
		}
		if (logToDatabase) {
			loggers.add(DatabaseLoggerCreator.getInstance().getLogger(dbParamsMap, Logger.getLogger("MyLog")));
		}
		
	}

	/**
	 * 
	 * @param messageText
	 * @param type
	 * @throws Exception
	 */
	public static void logMessage(String messageText, MessageType type) throws Exception {
		
		if (messageText == null) {
			return;
		}	
		if (messageText.trim().length() == 0) {
			return;
		}
		
		if (type == null) {
			throw new Exception("Error or Warning or Message must be specified");
		}
		
		for (ILogger item : loggers) {
			StringBuilder markedMessage = null;
			if (logMessage && MessageType.MESSAGE.equals(type)) {
				markedMessage = buildMarkedMessage(MessageType.MESSAGE.getValue(), messageText);
				if (item.isDatabaseLogger()){
					markedMessage = buildDatabaseMessage(markedMessage.toString(), String.valueOf(1)); 
				}					
			}
			if (logError && MessageType.ERROR.equals(type)) {
				markedMessage = buildMarkedMessage(MessageType.ERROR.getValue(), messageText);
				if (item.isDatabaseLogger()){
					markedMessage = buildDatabaseMessage(markedMessage.toString(), String.valueOf(2)); 
				}
			}
			if (logWarning && MessageType.WARNING.equals(type)) {
				markedMessage = buildMarkedMessage(MessageType.WARNING.getValue(), messageText);
				if (item.isDatabaseLogger()){
					markedMessage = buildDatabaseMessage(markedMessage.toString(), String.valueOf(3)); 
				}
			}
			item.log(markedMessage.toString());
		}

	}
		
	/**
	 * 
	 * @param messageType
	 * @param messageText
	 * @return
	 */
	private static StringBuilder buildMarkedMessage(String messageType, String messageText) {
		StringBuilder message = new StringBuilder();
		message.append(messageType);
		message.append(" ");
		message.append(DateFormat.getDateInstance(DateFormat.LONG).format(new Date()));
		message.append(messageText);
		return message;
	}

	/**
	 * 
	 * @param messageType
	 * @param messageText
	 * @return
	 */
	private static StringBuilder buildDatabaseMessage(String messageText, String t) {
		StringBuilder message = new StringBuilder();
		message.append("insert into Log_Values('");
		message.append(messageText);
		message.append("', ");
		message.append(t);
		message.append(")");
		return message;
	}

}

