package com.sabab.service;

import java.util.List;

import com.sabab.models.Chat;
import com.sabab.models.User;

public interface ChatService {
	
	public Chat createChat(User reqUser, User user2);
	
	public Chat findChatById(Integer chatId) throws Exception;
	
	public List<Chat> findUsersChat(Integer userId);

}
