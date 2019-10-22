package com.revature.service;

import java.util.List;

import com.revature.pojos.Message;

public interface MessageService {
	
	
	public boolean addMessageSent(Message message);
	
	public List<Message> getMessagesByUser(String email);

}
