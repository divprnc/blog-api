package com.app.blog.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CommentDto {
	private String id;
	@NotEmpty(message = "Name should not be null or empty")
	private String name;
	@Email(message = "Please Enter the valid email")
	@NotEmpty(message = "Name should not be null or empty")
	private String email;
	@NotEmpty(message = "Comment should not be null or empty")
	@Size(min = 10, message = "Comment body must be minimum 10 characters")
	private String body;

	public CommentDto() {
		super();
	}

	public CommentDto(String id, String name, String email, String body) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.body = body;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getBody() {
		return body;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
