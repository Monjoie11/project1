package com.revature.dao;

import java.util.List;

import com.revature.pojos.Message;

public interface MessageDao {

	public Message getMessage(int messageId);
	
	public boolean createMessage(Message message);
	
	public List<Message> getAllMessages(String email);
	
	public void updateMessage(Message message);
	
	public void deleteMessage(Message message);
	
}
