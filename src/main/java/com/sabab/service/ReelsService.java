package com.sabab.service;

import java.util.List;

import com.sabab.models.Reels;
import com.sabab.models.User;

public interface ReelsService {
	
	public Reels createReel(Reels reel, User user);
	
	public List<Reels> findAllReels();
	
	public List<Reels> findUsersReels(Integer userId) throws Exception;

}
