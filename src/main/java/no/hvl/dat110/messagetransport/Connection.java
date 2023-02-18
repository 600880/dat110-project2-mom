package no.hvl.dat110.messagetransport;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class Connection {

	private DataOutputStream outStream; // for writing bytes to the TCP connection
	private DataInputStream inStream; // for reading bytes from the TCP connection
	private Socket socket; // socket for the underlying TCP connection

	public Connection(Socket socket) {

		try {

			this.socket = socket;

			outStream = new DataOutputStream(socket.getOutputStream());

			inStream = new DataInputStream (socket.getInputStream());

		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void send(TransportMessage message) {

		// TODO 
		// encapsulate the data contained in the message and write to the output stream
		
		try {

			byte[] sendbuf = message.encapsulate();

			outStream.write(sendbuf);

		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}

	}

	public boolean hasData () {
		
		boolean hasdata = false;
		
		try {
			
			hasdata = inStream.available() > 0;
			
		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
		
		return hasdata;
	}

	public TransportMessage receive() {

		TransportMessage message;
		byte[] recvbuf;
		
		// TODO
		// read a segment from the input stream and decapsulate into message
		
		recvbuf = new byte[MessageConfig.SEGMENTSIZE];
		
		try {
				
			int read = inStream.read(recvbuf,0,MessageConfig.SEGMENTSIZE);
			
			if (read != MessageConfig.SEGMENTSIZE) {
				throw new IOException("receive - missing data");
			}
				
		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			// ex.printStackTrace();
		}
		
		message = new TransportMessage();
		
		message.decapsulate(recvbuf);
		
		return message;
		
	}

	// close the connection by closing streams and the underlying socket
	public void close() {

		try {
			
			outStream.close();
			inStream.close();

			socket.close();
		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}