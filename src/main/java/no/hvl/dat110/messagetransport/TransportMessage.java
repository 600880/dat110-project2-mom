package no.hvl.dat110.messagetransport;

import java.util.Arrays;

public class TransportMessage {

	private byte[] payload;

	public TransportMessage(byte[] payload) {
		// TODO: check for length within boundary
		if (payload == null || (payload.length + 1 > MessageConfig.SEGMENTSIZE)) {
			throw new RuntimeException("Message: invalid payload");
		}
		
		this.payload = payload; 
	}

	public TransportMessage() {
		super();
	}

	public byte[] getData() {
		return this.payload; 
	}

	public byte[] encapsulate() {
		
		byte[] encoded;
		
		// TODO
		// encapulate/encode the payload of the message
		
		encoded = new byte[MessageConfig.SEGMENTSIZE];

		encoded[0] =  (byte)(payload.length);

		for (int i = 0;i<payload.length;i++) {
			encoded[i+1] = payload[i];
		}
		
		return encoded;
		
	}

	public void decapsulate(byte[] received) {

		// TODO
		// decapsulate data in received and put in payload
		
		int len = received[0];
		
		payload = Arrays.copyOfRange(received,1,len+1);
		
	}
}
