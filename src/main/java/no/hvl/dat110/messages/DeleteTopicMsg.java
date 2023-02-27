package no.hvl.dat110.messages;

public class DeleteTopicMsg extends Message {

	// message sent from client to create topic on the broker
	String topic;

    public DeleteTopicMsg(String user, String topic) {
    	
    	super(MessageType.CREATETOPIC, user);
    	this.topic = topic;

    }
    
    public String getTopic() {
    	return topic;
    }
    
    public void setTopic(String topic) {
    	this.topic = topic;
    }
    
    @Override
    public String toString() {
    	return this.getClass().getSimpleName() + " topic=" + topic;
    }

	// TODO:
	// Implement object variables - a topic is required

	// Complete the constructor, get/set-methods, and toString method
	// as described in the project text

}
