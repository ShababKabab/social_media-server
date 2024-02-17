package com.sabab.service;

import java.util.List;

import com.sabab.models.Chat;
import com.sabab.models.Message;
import com.sabab.models.User;

public interface MessageService {
	
	public Message createMessage(User user, Integer chatId, Message req) throws Exception;
	
	public List<Message> findChatsMessages(Integer chatId) throws Exception;

}
