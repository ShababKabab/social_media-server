package com.sabab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sabab.models.Story;
import com.sabab.models.User;
import com.sabab.service.StoryService;
import com.sabab.service.UserService;

@RestController
public class StoryController {
	
	@Autowired
	private StoryService storyService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/story")
	public Story createStory(@RequestBody Story story, @RequestHeader("Authorization") String jwt) {
		
		User reqUser=userService.findUserByJwt(jwt);
		return storyService.createStory(story, reqUser);
	}
	
	@GetMapping("/api/story/user/{userId}")
	public List<Story> findUsersStory(@PathVariable Integer userId, @RequestHeader("Authorization") String jwt) throws Exception {
		
//		User reqUser=userService.findUserByJwt(jwt);
		return storyService.findStoryByUserId(userId);
	}

}
