package no.hvl.dat110.messages;

public class DisconnectMsg extends Message {
	
	// message sent from the client when disconnecting from the broker
	public DisconnectMsg(String user) {
		super(MessageType.DISCONNECT, user);
	}
	
}
