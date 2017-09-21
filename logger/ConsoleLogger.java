package logger;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsoleLogger implements ILogger {
	
	private ConsoleHandler ch;
	private Logger logger;	
	
	public ConsoleLogger(){
		 this.ch = new ConsoleHandler();
	}

	@Override
	public void log(String message) throws Exception {
		if(message.length() > 0) {
			logger.addHandler(this.ch);
			logger.log(Level.INFO, message);
		}
	}

	@Override
	public boolean isDatabaseLogger() {
		return false;
	};

}
