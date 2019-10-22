package com.revature.service;

import java.util.List;

import com.revature.dao.MessageJDBC;
import com.revature.pojos.Message;

public class MessageImpl implements MessageService {
	MessageJDBC messageJDBC = new MessageJDBC();

	@Override
	public boolean addMessageSent(Message message) {
		
		messageJDBC.createMessage(message);
		
		return true;
	}

	@Override
	public List<Message> getMessagesByUser(String email) {
		
		return messageJDBC.getAllMessages(email);
	}

}
