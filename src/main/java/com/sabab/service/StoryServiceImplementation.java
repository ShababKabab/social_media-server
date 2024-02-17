package com.sabab.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sabab.models.Story;
import com.sabab.models.User;
import com.sabab.repository.StoryRepository;

@Service
public class StoryServiceImplementation implements StoryService{
	
	@Autowired
	private StoryRepository storyRepository;
	
	@Autowired
	private UserService userService;

	@Override
	public Story createStory(Story story, User user) {
		
		story.setUser(user);
		story.setTimestamp(LocalDateTime.now());
		
		return storyRepository.save(story);
	}

	@Override
	public List<Story> findStoryByUserId(Integer userId) throws Exception {
		
		User user=userService.findUserById(userId);
		
		return storyRepository.findByUserId(userId);
	}

}
