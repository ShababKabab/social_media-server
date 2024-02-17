package com.sabab.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sabab.models.Chat;
import com.sabab.models.Message;
import com.sabab.models.User;
import com.sabab.repository.ChatRepository;
import com.sabab.repository.MessageRepository;

@Service
public class MessageServiceImplementation implements MessageService{
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private ChatService chatService;

	@Autowired
	private ChatRepository chatRepository;
	
	@Override
	public Message createMessage(User user, Integer chatId, Message req) throws Exception {
		
		Chat chat=chatService.findChatById(chatId);
		
		Message message = new Message();
		message.setContent(req.getContent());
		message.setImage(req.getImage());
		message.setUser(user);
		message.setChat(chat);
		message.setTimestamp(LocalDateTime.now());
		
		Message savedMessage = messageRepository.save(message);
		
		chat.getMessage().add(message);
		chatRepository.save(chat);
		
		return savedMessage;
	}

	@Override
	public List<Message> findChatsMessages(Integer chatId) throws Exception {
		
		Chat chat=chatService.findChatById(chatId);
		
		
		return messageRepository.findByChatId(chatId);
	}

}
