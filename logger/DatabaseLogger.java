package logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseLogger implements ILogger {
	
	private Connection connection;
	
	public DatabaseLogger(String dbms, String serverName, String portNumber, String userName, String password) throws Exception {
		StringBuilder url = new StringBuilder();
		url.append("jdbc:");
		url.append(dbms);
		url.append("://");
		url.append(serverName);
		url.append(":");
		url.append(portNumber);
		url.append("/");		
		Properties connectionProps = new Properties();
		connectionProps.put("user", userName);
		connectionProps.put("password", password);
		
		this.connection = DriverManager.getConnection(url.toString(), connectionProps);
	}

	@Override
	public void log(String message) throws Exception {
		if(message.length() > 0) {
			this.connection.createStatement().executeUpdate(message);
		}
	};

	@Override
	public boolean isDatabaseLogger() {
		return true;
	};
	
}
