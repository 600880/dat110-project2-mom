package no.hvl.dat110.messages;

public abstract class Message {

	// base class for messages exchanged between broker and clients
	private MessageType type;
	private String user;
	
	public Message() {
		
	}
	
	public Message(MessageType type, String user) {
		this.type = type;
		this.user = user;
	}

	public MessageType getType() { return this.type; }

	
	public String getUser() {
		return user;
	}

	@Override
	public String toString() {
		return "Message [type=" + type + ", user=" + user + "]";
	};
	
	
}
