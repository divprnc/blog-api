package com.app.blog.service;



import java.util.List;

import com.app.blog.payload.PostDto;
import com.app.blog.payload.PostResponse;

public interface PostService {
	PostDto createPost(PostDto postDto);

	PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

	PostDto getPostById(String id);

	PostDto updatePostById(PostDto postDto, String postId);

	void deletePostById(String postId);
	
	List<PostDto> getPostsByCategory(String categoryId);

}
