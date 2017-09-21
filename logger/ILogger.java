package logger;

public interface ILogger {
	
	public void log(String message) throws Exception;
	public boolean isDatabaseLogger();

}
