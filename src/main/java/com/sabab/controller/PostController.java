package com.sabab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.sabab.config.JwtProvider;
import com.sabab.models.Post;
import com.sabab.models.User;
import com.sabab.repository.PostRepository;
import com.sabab.response.ApiResponse;
import com.sabab.service.PostService;
import com.sabab.service.UserService;

@RestController
public class PostController {

	@Autowired
	UserService userService;
	
	@Autowired
	PostService postService;
	
	@PostMapping("/api/posts")
	public ResponseEntity<Post> createPost(@RequestBody Post post,@RequestHeader("Authorization") String jwt) throws Exception{
//		System.out.println(postRepository.findB);
		User reqUser=userService.findUserByJwt(jwt);
		return new ResponseEntity<Post>(postService.createNewPost(post,reqUser.getId()),HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/api/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId,@RequestHeader("Authorization") String jwt) throws Exception{
		User reqUser=userService.findUserByJwt(jwt);
		String message=postService.deletePost(postId, reqUser.getId());
		ApiResponse res=new ApiResponse(message,true);
		return new ResponseEntity<ApiResponse>(res,HttpStatus.OK);
	}
	
	@GetMapping("/api/posts/{postId}")
	public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId) throws Exception{
		return new ResponseEntity<Post>(postService.findPostById(postId),HttpStatus.ACCEPTED);
	}

	@GetMapping("/api/posts/user/{userId}")
	public ResponseEntity<List<Post>> findUsersPost(@PathVariable Integer userId){
		List<Post> posts=postService.findPostByUserId(userId);
		return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
	}
	
	@GetMapping("/api/posts")
	public ResponseEntity<List<Post>> findAllPost(){
		return new ResponseEntity<List<Post>>(postService.findAllPost(),HttpStatus.OK);
	}
	
	@PutMapping("/api/posts/save/{postId}")
	public ResponseEntity<Post> savePostHandler(@PathVariable Integer postId,@RequestHeader("Authorization") String jwt) throws Exception{
		User reqUser=userService.findUserByJwt(jwt);
		return new ResponseEntity<Post>(postService.savedPost(postId, reqUser.getId()),HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/api/posts/like/{postId}")
	public ResponseEntity<Post> likePostHandler(@PathVariable Integer postId,@RequestHeader("Authorization") String jwt) throws Exception{
		User reqUser=userService.findUserByJwt(jwt);
		return new ResponseEntity<Post>(postService.likePost(postId, reqUser.getId()),HttpStatus.ACCEPTED);
	}
}
