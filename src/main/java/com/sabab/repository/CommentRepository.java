package com.sabab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sabab.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
