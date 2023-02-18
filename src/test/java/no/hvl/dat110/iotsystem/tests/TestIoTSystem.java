package no.hvl.dat110.iotsystem.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import no.hvl.dat110.broker.BrokerServer;
import no.hvl.dat110.iotsystem.DisplayDevice;
import no.hvl.dat110.iotsystem.TemperatureDevice;

public class TestIoTSystem {

	@Test
	public void test() {

		System.out.println("IoT system starting ...");

		Runnable display = () -> DisplayDevice.main(null);
		Runnable sensor = () -> TemperatureDevice.main(null);
		Runnable broker = () -> BrokerServer.main(null);

		Thread displaythread = new Thread(display);
		Thread sensorthread = new Thread(sensor);
		Thread brokerthread = new Thread(broker);

		System.out.println("Starting broker ...");

		brokerthread.start();

		// allow broker to reaching waiting for incoming connections
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Starting display ...");
		
		displaythread.start();

		// allow display to create topic
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Starting sensor ...");
		sensorthread.start();

		try {

			displaythread.join();
			sensorthread.join();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// we check only termination here
		assertTrue(true);

		System.out.println("IoT system stopping ...");
	}
}
