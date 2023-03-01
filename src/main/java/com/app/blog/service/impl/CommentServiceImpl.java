package com.app.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.app.blog.entity.Comment;
import com.app.blog.entity.Post;
import com.app.blog.exception.BlogApiException;
import com.app.blog.exception.ResourceNotFoundException;
import com.app.blog.payload.CommentDto;
import com.app.blog.repository.CommentRepository;
import com.app.blog.repository.PostRepository;
import com.app.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	private CommentRepository commentRepository;
	private PostRepository postRepository;
	private ModelMapper modelMapper;

	public CommentServiceImpl(CommentRepository commentRepository, ModelMapper modelMapper,
			PostRepository postRepository) {
		super();
		this.commentRepository = commentRepository;
		this.modelMapper = modelMapper;
		this.postRepository = postRepository;
	}

	@Override
	public CommentDto createcomment(String postId, CommentDto commentDto) {
		Comment comment = modelMapper.map(commentDto, Comment.class);
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		comment.setPost(post);
		Comment savedComment = commentRepository.save(comment);
		return modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public List<CommentDto> getCommentsByPostId(String postId) {
		List<Comment> comments = commentRepository.findByPostId(postId);

		return comments.stream().map(comment -> modelMapper.map(comment, CommentDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public CommentDto getcommentById(String postId, String commentId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		// Retrieve comment by id

		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

		if (!comment.getPost().getId().equals(post.getId())) {
			throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
		}
		return modelMapper.map(comment, CommentDto.class);
	}

	@Override
	public CommentDto updateComment(String postId, String commentId, CommentDto commentDto) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
		if (!comment.getPost().getId().equals(post.getId())) {
			throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
		}
		comment.setName(commentDto.getName());
		comment.setEmail(commentDto.getEmail());
		comment.setBody(commentDto.getBody());
		Comment updatedComment = commentRepository.save(comment);
		return modelMapper.map(updatedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(String postId, String commentId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
		if (!comment.getPost().getId().equals(post.getId())) {
			throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
		}
		commentRepository.delete(comment);
	}

}
