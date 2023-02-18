package no.hvl.dat110.common;

public abstract class Stopable extends Thread {

	private boolean stop = false;
	protected String name;
	
	public Stopable(String name) {
		this.name = name;
	}
	
	public synchronized void doStop() {
		stop = true;
	}

	private synchronized boolean doCont() {
		return !stop;

	}

	public abstract void doProcess();
	
	public void run() {

		Logger.log(name + " running");
		
		while (doCont()) {

			doProcess();
			
		}

		Logger.log(name + " stopping");

	}
}
