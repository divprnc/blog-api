package com.app.blog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.blog.payload.PostDto;
import com.app.blog.payload.PostResponse;
import com.app.blog.service.PostService;
import com.app.blog.utils.AppConstants;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	private PostService postService;

	public PostController(PostService postService) {
		super();
		this.postService = postService;
	}

	// Create Post
	// Authenticate which have only admin role
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
		return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
	}

	// Get All Posts & Pagination
	@GetMapping
	public PostResponse getAllPost(
			@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
		return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
	}

	// Get Post By Id
	@GetMapping("/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable String postId) {
		return ResponseEntity.ok(postService.getPostById(postId));
	}

	// Update Post
	// Authenticate which have only admin role
	@PutMapping("/{postId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<PostDto> updatePostById(@Valid @RequestBody PostDto postDto, @PathVariable String postId) {
		return new ResponseEntity<>(postService.updatePostById(postDto, postId), HttpStatus.OK);
	}

	// Update Post
	// Authenticate which have only admin role
	@DeleteMapping("/{postId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deletePostById(@PathVariable String postId) {
		postService.deletePostById(postId);
		return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
	}
	
	
	
	// Build Get Posts by Category REST API
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable String categoryId) {
		List<PostDto> postDtos = postService.getPostsByCategory(categoryId);
		return ResponseEntity.ok(postDtos);
	}
	
	
	
}
