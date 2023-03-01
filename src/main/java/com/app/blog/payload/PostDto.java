package com.app.blog.payload;

import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PostDto {
	private String id;
	@NotEmpty
	@Size(min = 2, message = "Post title should have at least 2 characters")
	private String title;
	@NotEmpty
	@Size(min = 2, message = "Post description should have at least 2 characters")
	private String description;
	@NotEmpty
	private String content;
	private Set<CommentDto> comments;
	private String categoryId;

	public PostDto() {
		super();
	}

	public PostDto(String id, String title, String description, String content, Set<CommentDto> comments,
			String categoryId) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.content = content;
		this.comments = comments;
		this.categoryId = categoryId;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getContent() {
		return content;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Set<CommentDto> getComments() {
		return comments;
	}

	public void setComments(Set<CommentDto> comments) {
		this.comments = comments;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
}
