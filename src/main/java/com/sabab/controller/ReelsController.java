package com.sabab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.sabab.models.Reels;
import com.sabab.models.User;
import com.sabab.service.ReelsService;
import com.sabab.service.UserService;

@RestController
public class ReelsController {
	
	@Autowired
	private ReelsService reelsService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/reels")
	public Reels createdReels(@RequestBody Reels reel,@RequestHeader("Authorization") String jwt) {

		User user=userService.findUserByJwt(jwt);
		
		return reelsService.createReel(reel, user);
	}
	
	@GetMapping("/api/reels")
	public List<Reels> findAllReels() {
		
		return reelsService.findAllReels();
	}
	
	@GetMapping("/api/reels/user/{userId}")
	public List<Reels> findUserReels(@PathVariable Integer userId) throws Exception {
		return reelsService.findUsersReels(userId);
	}
	

}
