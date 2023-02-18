package no.hvl.dat110.broker.processing.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import no.hvl.dat110.broker.Broker;
import no.hvl.dat110.broker.BrokerServer;
import no.hvl.dat110.broker.Dispatcher;
import no.hvl.dat110.client.Client;

public class Test4CreateDelete extends Test0Base {

	private static String TESTTOPIC = "testtopic";
	
	@Test
	public void test() {
				
		Client client = new Client("client",BROKER_TESTHOST,BROKER_TESTPORT);
		
		broker.setMaxAccept(1);
		
		client.connect();
		
		client.createTopic(TESTTOPIC);
		
		client.deleteTopic(TESTTOPIC);
		
		client.disconnect();
	
		assertTrue(true);
	}

}
