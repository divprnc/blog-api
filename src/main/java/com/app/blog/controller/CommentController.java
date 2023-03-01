package com.app.blog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.blog.payload.CommentDto;
import com.app.blog.service.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class CommentController {
	private CommentService commentService;

	public CommentController(CommentService commentService) {
		super();
		this.commentService = commentService;
	}

	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@Valid @PathVariable String postId, @RequestBody CommentDto commentDto) {
		return new ResponseEntity<>(commentService.createcomment(postId, commentDto), HttpStatus.CREATED);
	}

	@GetMapping("/posts/{postId}/comments")
	public List<CommentDto> getCommentsByPostId(@PathVariable String postId) {
		return commentService.getCommentsByPostId(postId);
	}

	@GetMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto> getCommentsById(@PathVariable String postId, @PathVariable String commentId) {
		return new ResponseEntity<>(commentService.getcommentById(postId, commentId), HttpStatus.OK);
	}

	@PutMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto> updateComment(@Valid @PathVariable String postId, @PathVariable String commentId,
			@RequestBody CommentDto commentDto) {
		return new ResponseEntity<>(commentService.updateComment(postId, commentId, commentDto), HttpStatus.OK);
	}

	@DeleteMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<String> deleteComment(@PathVariable String postId, @PathVariable String commentId) {
		commentService.deleteComment(postId, commentId);
		String statement = "Comment Deleted for postid " + postId;
		return new ResponseEntity<>(statement, HttpStatus.OK);
	}

}
