package no.hvl.dat110.broker.processing.tests;

import org.junit.jupiter.api.Test;

import no.hvl.dat110.broker.Broker;
import no.hvl.dat110.broker.Dispatcher;
import no.hvl.dat110.client.Client;

public class Test5Subscribe extends Test0Base {

	public static String TESTTOPIC = "testtopic";
	
	@Test
	public void test() {
		
		broker.setMaxAccept(1);
		
		Client client = new Client("client",BROKER_TESTHOST,BROKER_TESTPORT);
		
		client.connect();
		
		client.createTopic(TESTTOPIC);
		
		client.subscribe(TESTTOPIC);
		
		client.unsubscribe(TESTTOPIC);
		
		client.disconnect();
	
		
	}

}
