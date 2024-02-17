package com.sabab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sabab.models.Reels;
import com.sabab.models.User;
import com.sabab.repository.ReelsRepository;

@Service
public class ReelsServiceImplementation implements ReelsService{

	@Autowired
	private ReelsRepository reelsRepository;

	@Autowired
	private UserService userService;
	@Override
	public Reels createReel(Reels reel, User user) {
		reel.setUser(user);
		return reelsRepository.save(reel);
	}

	@Override
	public List<Reels> findAllReels() {
		// TODO Auto-generated method stub
		return reelsRepository.findAll();
	}

	@Override
	public List<Reels> findUsersReels(Integer userId) throws Exception {
		// TODO Auto-generated method stub
		userService.findUserById(userId);
		return reelsRepository.findByUserId(userId);
	}

}
