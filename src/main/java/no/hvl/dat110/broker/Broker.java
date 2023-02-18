package no.hvl.dat110.broker;

import no.hvl.dat110.common.Logger;
import no.hvl.dat110.common.Stopable;
import no.hvl.dat110.messages.ConnectMsg;
import no.hvl.dat110.messages.Message;
import no.hvl.dat110.messages.MessageType;
import no.hvl.dat110.messages.MessageUtils;
import no.hvl.dat110.messagetransport.Connection;
import no.hvl.dat110.messagetransport.MessagingServer;

public class Broker extends Stopable { 

	private boolean stopable = false;
	private int maxaccept = 0;
	
	private MessagingServer server;
	private Dispatcher dispatcher;
		
	public Broker (Dispatcher dispatcher,int port) {
		super("Broker");
		server = new MessagingServer(port);
		this.dispatcher = dispatcher;
	}
	
	public void setMaxAccept(int n) {
		this.stopable = true;
		this.maxaccept = n;
		
	}
	
	@Override
	public void doProcess() {
					
			Logger.log("Broker accept [" + maxaccept  + "]");
			
			Connection connection = server.accept();
			
			Logger.log("!" + maxaccept);
			
			waitConnect(connection);
		
			if (stopable) {
				
				maxaccept--;
				
				if (maxaccept < 1) {
					
					super.doStop();
				}
			}
	}
	
	private void waitConnect(Connection connection) {
				
		Message msg = MessageUtils.receive(connection);
		
		if (msg.getType() == MessageType.CONNECT) {
			
			ConnectMsg cmsg = (ConnectMsg) msg;
			dispatcher.onConnect(cmsg, connection);
			
		} else {
			System.out.println("Protocol error: first message should be connect");
		}
	}
	
}
