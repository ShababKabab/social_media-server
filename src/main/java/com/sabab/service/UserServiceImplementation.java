package com.sabab.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sabab.config.JwtProvider;
import com.sabab.exceptions.UserException;
import com.sabab.models.User;
import com.sabab.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User registerUser(User user) {
		User savedUser = userRepository.save(user);
		return savedUser;
	}

	@Override
	public User findUserById(Integer userId) throws UserException {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
//			System.out.println(user.get().toString());
			return user.get();
		}
		throw new UserException("user not exist with userid " + userId);
	}

	@Override
	public User findUserByEmail(String email) {
		User theUser = userRepository.findByEmail(email);
		return theUser;
	}

	@Override
	public User followUser(Integer reqUserId, Integer userId2) throws UserException {
		User reqUser = findUserById(reqUserId);
		User user2 = findUserById(userId2);
		user2.getFollowers().add(reqUserId);
		reqUser.getFollowing().add(userId2);
		userRepository.save(reqUser);
		userRepository.save(user2);
		return reqUser;
	}

	@Override
	public List<User> searchUser(String query) {
		return userRepository.searchUser(query);
	}

	@Override
	public User updateUser(User user, Integer userId) throws UserException {
		User user1 = findUserById(userId);

		if (user.getFirstName() != null) {
			user1.setFirstName(user.getFirstName());
		}
		if (user.getLastName() != null) {
			user1.setLastName(user.getLastName());
		}
		if (user.getEmail() != null) {
			user1.setEmail(user.getEmail());
		}
		if (user.getGenders() != null) {
			user1.setGenders(user.getGenders());
		}
		User updatedUser = userRepository.save(user1);
		return updatedUser;
	}

	@Override
	public User findUserByJwt(String jwt) {
		
		String email=JwtProvider.getEmailFromJwtToken(jwt);
		User user=userRepository.findByEmail(email);
//		user.setPassword(null);
		return user;
	}

}
