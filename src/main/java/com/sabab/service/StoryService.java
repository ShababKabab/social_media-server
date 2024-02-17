package com.sabab.service;

import java.util.List;

import com.sabab.models.Story;
import com.sabab.models.User;

public interface StoryService{
	
	public Story createStory(Story story, User user);
	
	public List<Story> findStoryByUserId(Integer userId) throws Exception;

}
