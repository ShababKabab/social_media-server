package com.sabab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.sabab.models.Message;
import com.sabab.models.User;
import com.sabab.service.MessageService;
import com.sabab.service.UserService;

@RestController
public class CreateMessage {
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/messages/chat/{chatId}")
	public Message createMessage(@RequestBody Message req, @RequestHeader("Authorization") String jwt, @PathVariable Integer chatId) throws Exception {
		
		User user=userService.findUserByJwt(jwt);
		
		return messageService.createMessage(user, chatId, req);
		
	}
	
	@GetMapping("/api/messages/chat/{chatId}")
	public List<Message> findChatsMessage(@PathVariable Integer chatId) throws Exception {
		
		return messageService.findChatsMessages(chatId);
		
	}

}
