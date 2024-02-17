package com.sabab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sabab.models.Story;

public interface StoryRepository extends JpaRepository<Story, Integer>{
	
	public List<Story> findByUserId(Integer userId);

}
