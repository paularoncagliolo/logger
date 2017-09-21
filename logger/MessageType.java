package logger;

public enum MessageType {
	
	MESSAGE("Message"),
	ERROR("Error"),
	WARNING("Warning");
	
	private final String value;
	
	private MessageType(String value) {
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}

}
