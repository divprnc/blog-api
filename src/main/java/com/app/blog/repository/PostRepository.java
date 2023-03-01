package com.app.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.blog.entity.Post;

public interface PostRepository extends JpaRepository<Post, String>{
	List<Post> findByCategoryId(String categoryId);
}
