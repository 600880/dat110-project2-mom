package no.hvl.dat110.broker.processing.tests;

import org.junit.jupiter.api.Test;

import no.hvl.dat110.broker.Broker;
import no.hvl.dat110.broker.Dispatcher;
import no.hvl.dat110.client.Client;

public class Test2Reconnect extends Test0Base {

	@Test
	public void test() {
		
		broker.setMaxAccept(2);
		
		Client client1 = new Client("client1",BROKER_TESTHOST,BROKER_TESTPORT);
		
		client1.connect();
		
		client1.disconnect();
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		client1.connect();
		
		client1.disconnect();
		
	}
}
