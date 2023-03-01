package com.app.blog.service;

import java.util.List;

import com.app.blog.payload.CommentDto;

public interface CommentService {
	CommentDto createcomment(String postId, CommentDto commentDto);

	List<CommentDto> getCommentsByPostId(String postId);

	CommentDto getcommentById(String postId, String commentId);

	CommentDto updateComment(String postId, String commentId, CommentDto commentDto);
	
	void deleteComment(String postId, String commentId);
}
